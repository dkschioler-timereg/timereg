package dk.schioler.tools.timeregistration.input.menu;

import java.util.ArrayList;
import java.util.List;




import dk.schioler.tools.timeregistration.model.Task;
import dk.schioler.tools.timeregistration.model.Project;

public class Menu {
	List<MenuCategory> categories = new ArrayList<MenuCategory>();

	int catIdx = 1;
	int idxSrc = 1;

	public boolean addCategory(Project project) {
		boolean added = categories.add(new MenuCategory(catIdx, project));
		catIdx++;
		return added;
	}

	public boolean addTask(Task task) {
		boolean added = false;
		for (MenuCategory menuCategory : categories) {
			if (menuCategory.project.equals(task.getProject())) {
				added = menuCategory.addItem(idxSrc, task);
				idxSrc++;
				break;
			}
		}
		return added;
	}

	public MenuCategory getCategory(int idx) {
		for (MenuCategory menuCategory : categories) {
			if (menuCategory.idx == idx) {
				return menuCategory;
			}
		}
		return null;
	}

	public Task getTask(String selection) {
		Task t = null;
		for (MenuCategory menuCategory : categories) {
			t = menuCategory.getTask(selection);
			if (t != null) {
				break;
			}
		}
		return t;
	}

	public List<MenuCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<MenuCategory> categories) {
		this.categories = categories;
	}
	
	
}