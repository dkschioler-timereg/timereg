package dk.schioler.tools.timeregistration.input.menu;

import java.util.ArrayList;
import java.util.List;




import dk.schioler.tools.timeregistration.model.Task;
import dk.schioler.tools.timeregistration.model.Project;

public class MenuCategory implements Comparable<MenuCategory> {
	int idx;
	Project project;
	List<MenuItem> items = new ArrayList<MenuItem>();

	public Task getTask(String selection) {
		Task t = null;
		for (MenuItem menuItem : items) {
			if (menuItem.idx == Integer.parseInt(selection)) {
				t = menuItem.task;
				break;
			}
		}
		return t;
	}

	public MenuCategory(int idx, Project project) {
		this.idx = idx;
		this.project = project;
	}

	public boolean addItem(int idx, Task task) {
		return items.add(new MenuItem(idx, this, task));
	}

	@Override
	public int compareTo(MenuCategory arg0) {
		return idx - arg0.idx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
	

}