package ua.kharkov.khpi.belozerov.db.entity;

public class LocaleBean {
	private String locale;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	@Override
	public String toString() {
		return "LocaleBean [locale=" + locale + "]";
	}
	
}
