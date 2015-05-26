package dk.bitmovers.timeregistration.client.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dk.bitmovers.timeregistration.client.view.IndexView;
import dk.bitmovers.timeregistration.client.view.PreferencesView;
import dk.bitmovers.timeregistration.client.view.ProviderView;
import dk.bitmovers.timeregistration.client.view.RegistrationView;
import dk.bitmovers.timeregistration.client.view.ReportsView;
import dk.bitmovers.timeregistration.client.view.TimeRegistrationView;

public class ViewManager {



	public static final ViewInfo INDEX = new ViewInfo("navigation.home", "", IndexView.class);
	public static final ViewInfo PROVIDER = new ViewInfo("navigation.providers", ProviderView.class.getSimpleName(), ProviderView.class);
	public static final ViewInfo REGISTRATION = new ViewInfo("navigation.registration", RegistrationView.class.getSimpleName(), RegistrationView.class);
	public static final ViewInfo REPORTS = new ViewInfo("navigation.reports", ReportsView.class.getSimpleName(), ReportsView.class);
	public static final ViewInfo PREFERENCES = new ViewInfo("navigation.preferences", PreferencesView.class.getSimpleName(), PreferencesView.class);

	public static final List<ViewInfo> VIEWS;
	static {

		List<ViewInfo> tmp = new ArrayList<ViewManager.ViewInfo>();
		tmp.add(INDEX);
		tmp.add(PROVIDER);
		tmp.add(REGISTRATION);
		tmp.add(REPORTS);
		tmp.add(PREFERENCES);
		VIEWS = Collections.unmodifiableList(tmp);
	}

	public static final class ViewInfo {
		public final String labelKey;
		public final String navigateTo;
		public final Class<? extends TimeRegistrationView> implementation;

		public ViewInfo(String labelKey, String navigateTo, Class<? extends TimeRegistrationView> implementation) {
			super();
			this.labelKey = labelKey;
			this.navigateTo = navigateTo;
			this.implementation = implementation;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((labelKey == null) ? 0 : labelKey.hashCode());
			result = prime * result + ((navigateTo == null) ? 0 : navigateTo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ViewInfo other = (ViewInfo) obj;
			if (labelKey == null) {
				if (other.labelKey != null)
					return false;
			} else if (!labelKey.equals(other.labelKey))
				return false;
			if (navigateTo == null) {
				if (other.navigateTo != null)
					return false;
			} else if (!navigateTo.equals(other.navigateTo))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ViewInfo [labelKey=" + labelKey + ", navigateTo=" + navigateTo + ", implementation=" + implementation + "]";
		}

	}

}
