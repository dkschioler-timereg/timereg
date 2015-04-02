package dk.schioler.tools.timeregistration.input.menu;

import dk.schioler.tools.timeregistration.model.Task;

public class MenuItem implements Comparable<MenuItem> {
	int idx;
	MenuCategory category;
	Task task;

	public MenuItem(int idx, MenuCategory category, Task task) {
		super();
		this.idx = idx;
		this.category = category;
		this.task = task;
	}

	@Override
	public int compareTo(MenuItem arg0) {
		return this.idx - arg0.idx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public MenuCategory getCategory() {
		return category;
	}

	public void setCategory(MenuCategory category) {
		this.category = category;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

}
