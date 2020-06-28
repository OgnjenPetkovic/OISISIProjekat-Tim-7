package view.report;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerToolbar;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;

@SuppressWarnings("serial")
public class CustomJRViewer extends JRViewer {
	
	public CustomJRViewer(JasperPrint jrPrint) {
		super(jrPrint);
	}
	
	@Override
	protected JRViewerToolbar createToolbar()
	{
		JRViewerToolbar retVal = new JRViewerToolbar(viewerContext);
		JRSaveContributor[] jrs = { new JRPdfSaveContributor(viewerContext.getLocale(), viewerContext.getResourceBundle()) };
		retVal.setSaveContributors(jrs);
		return retVal;
	}
	
}
