package logger;

import java.io.Serializable;

import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.testng.Reporter;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporterAppender.
 */
@Plugin(name = "Reporter", category = "Core", elementType = "appender", printObject = true)
public class ReporterAppender extends AbstractAppender {

	/**
	 * Instantiates a new reporter appender.
	 *
	 * @param name the name
	 * @param layout the layout
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ReporterAppender(final String name, final Layout layout) {
		super(name, null, layout, false);
	}

	/* (non-Javadoc)
	 * @see org.apache.logging.log4j.core.Appender#append(org.apache.logging.log4j.core.LogEvent)
	 */
	public void append(final LogEvent event) {
		final Layout<? extends Serializable> layout = getLayout();
		String m = event.getMessage().getFormattedMessage();
		if (layout != null && layout instanceof AbstractStringLayout) {
			Reporter.log(((AbstractStringLayout) layout).toSerializable(event));
		} else {
			Reporter.log(m);
		}
	}

	/**
	 * Creates the appender.
	 *
	 * @param name the name
	 * @param layout the layout
	 * @return the reporter appender
	 */
	@PluginFactory
	public static ReporterAppender createAppender(
			@PluginAttribute("name") @Required(message = "A name for the Appender must be specified") final String name,
			@PluginElement("Layout") Layout<? extends Serializable> layout) {
		return new ReporterAppender(name, layout);
	}
}