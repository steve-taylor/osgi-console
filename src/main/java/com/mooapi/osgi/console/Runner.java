package com.mooapi.osgi.console;

import javax.swing.SwingUtilities;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.shell.ShellService;

/**
 *
 * @author steve
 */
@Component(immediate = true)
public class Runner {

	@Activate
	protected void activate() {
		SwingUtilities.invokeLater(() -> {
			console = new OsgiConsole(shellService);
			console.setSize(600, 400);
			console.setLocationRelativeTo(null);
			console.setVisible(true);
		});
	}

	@Deactivate
	protected void deactivate() {
		SwingUtilities.invokeLater(() -> {
			console.setVisible(false);
			console = null;
		});
	}

	@Reference
	private ShellService shellService;

	private OsgiConsole console;
}
