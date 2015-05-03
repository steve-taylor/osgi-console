package com.atulos.osgi.console;

import java.util.HashMap;
import java.util.Map;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import org.apache.felix.shell.ShellService;

/**
 *
 * @author steve
 */
public class HelpTableModel implements TableModel {

	public HelpTableModel(ShellService shellService) {
		commands = shellService.getCommands();
		for (String command : commands) {
			descriptionByName.put(command, shellService.getCommandDescription(command));
			usageByName.put(command, shellService.getCommandUsage(command));
		}
	}

	@Override
	public int getRowCount() {
		return commands.length;
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
			case 0: return "Command";
			case 1: return "Description";
			case 2: return "Usage";
			default: return "";
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0: return commands[rowIndex];
			case 1: return descriptionByName.get(commands[rowIndex]);
			case 2: return usageByName.get(commands[rowIndex]);
			default: return "";
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
	}

	private final String[] commands;

	private final Map<String, String> descriptionByName = new HashMap<>();

	private final Map<String, String> usageByName = new HashMap<>();
}
