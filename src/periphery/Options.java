package periphery;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Options {

	private List<Option> options;

	public Options() {
		this.options = new ArrayList<>();
	}

	public Options(List<Option> options) {
		this.options = options;
	}

	public String[] getOptionNamesWithDefault(String defaultName) {
		Option[] optionsArray = new Option[this.options.size()];
		optionsArray = this.options.toArray(optionsArray);
		String[] result = new String[this.options.size() + 1];
		result[0] = defaultName;
		for (int i = 1; i < this.options.size() + 1; i++) {
			result[i] = optionsArray[i - 1].getClassName();
		}
		return result;
	}

	public String[] getOptionNames() {
		Option[] optionsArray = new Option[this.options.size()];
		optionsArray = this.options.toArray(optionsArray);
		String[] result = new String[this.options.size()];
		for (int i = 0; i < this.options.size(); i++) {
			result[i] = optionsArray[i].getClassName();
		}
		return result;
	}

	public Option getOption(String searchedName) {
		Object[] optionsArray = this.options.toArray();
		for (Object object : optionsArray) {
			Option posOption = (Option) object;
			if (posOption.getClassName()
					.equals(searchedName)) {
				return posOption;
			}
		}
		return null;
	}

	public void addOption(Option option) {
		this.options.add(option);
	}

	public void deleteOption(Option option) {
		this.options.remove(option);
	}

	public boolean OptionsEmpty() {
		return this.options.isEmpty();
	}

	public Vector<Option> getOptions() {
		return new Vector<>(this.options);
	}
}
