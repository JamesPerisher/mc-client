/*
 * Includes modified decompiled Replay Mod class files.
 *
 * License for Replay Mod:
 *
 *     Copyright (C) <year>  <name of author>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.mcblueparrot.client.mod.impl.replay.fix;

import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.google.common.util.concurrent.ListenableFutureTask;
import com.replaymod.core.mixin.MinecraftAccessor;
import com.replaymod.core.versions.scheduler.Scheduler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ReportedException;
import me.mcblueparrot.client.Client;
import me.mcblueparrot.client.event.EventHandler;
import me.mcblueparrot.client.event.impl.PreRenderTickEvent;

public class SCScheduler implements Scheduler {
	private static final Minecraft mc = Minecraft.getMinecraft();
	private boolean inRunLater = false;

	public void runSync(Runnable runnable) throws InterruptedException, ExecutionException, TimeoutException {
		if(mc.isCallingFromMinecraftThread()) {
			runnable.run();
		}
		else {
			FutureTask<Void> future = new FutureTask<>(runnable, null);
			runLater(future);
			future.get(30L, TimeUnit.SECONDS);
		}
	}

	public void runPostStartup(Runnable runnable) {
		this.runLater(runnable);
	}

	public void runLaterWithoutLock(Runnable runnable) {
		this.runLater(() -> this.runLaterWithoutLock(runnable), runnable);
	}

	public void runLater(Runnable runnable) {
		this.runLater(runnable, () -> this.runLater(runnable));
	}

	private void runLater(Runnable runnable, final Runnable defer) {
		if(mc.isCallingFromMinecraftThread() && inRunLater) {
			Client.INSTANCE.bus.register(new TickListener(defer));
		}
		else {
			Queue<FutureTask<?>> tasks = ((MinecraftAccessor) mc).getScheduledTasks();

			synchronized(tasks) {
				tasks.add(ListenableFutureTask.create(() -> {
					this.inRunLater = true;

					try {
						runnable.run();
					}
					catch (ReportedException error) {
						error.printStackTrace();
						System.err.println(error.getCrashReport().getCompleteReport());
						mc.crashed(error.getCrashReport());
					}
					finally {
						this.inRunLater = false;
					}
				}, null));
			}
		}
	}

	public void runTasks() {
	}

	
	static class TickListener {

		private Runnable defer;

		public TickListener(Runnable defer2) {
			this.defer = defer2;
		}

		@EventHandler
		public void onTick(PreRenderTickEvent event) {
			Client.INSTANCE.bus.unregister(this);
			defer.run();
		}

	}

}

