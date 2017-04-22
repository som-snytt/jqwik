package net.jqwik.execution;

import org.junit.platform.engine.*;

public class ExecutionPipeline {

	interface ExecutionTask {
		ExecutionTask predecessor();
		void execute(EngineExecutionListener listener);
	}

	private final EngineExecutionListener executionListener;

	public ExecutionPipeline(EngineExecutionListener executionListener) {
		this.executionListener = executionListener;
	}

	void submit(ExecutionTask task) {
	}

	void putInFront(ExecutionTask task) {

	}

	void run() {
	}

	boolean awaitTermination() {
		return true;
	}

}
