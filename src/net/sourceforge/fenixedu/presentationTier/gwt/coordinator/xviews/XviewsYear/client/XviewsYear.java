package net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client;

import net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client.widgets.averageByCurricularYears.CompositeAverageByCurricularYears;
import net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client.widgets.detailedBarPopup.DetailPopupCanvas;
import net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client.widgets.detailedInarPopup.DetailedInarPopup;
import net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client.widgets.inarByCurricularYears.CompositeInarByCurricularYears;
import net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client.widgets.inarByCurricularYears.InarByCurricularYears;
import net.sourceforge.fenixedu.presentationTier.gwt.coordinator.xviews.XviewsYear.client.widgets.inarCaption.InarCaption;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class XviewsYear implements EntryPoint {
    
    private final native String getArgs(String paramId) /*-{
        try{
            var myArg = $wnd.document.getElementById(paramId).value;
            return myArg;
        } catch(e) {
            alert(e);
        }
    }-*/;
    
    
    public RootPanel content;
    public RootPanel shader;
    public RootPanel overlay;
    public DetailPopupCanvas popupCanvas;
    public DetailedInarPopup popupInar;
    
    public VerticalPanel vPanel;
    public HorizontalPanel rowOne;
    public HorizontalPanel rowTwo;
    
    public String dcpId;
    public String eyId;
    public InarServiceAsync inarService;
    
    public void focus() {
	shader.setVisible(true);
	overlay.setVisible(true);
    }
    
    public void defocus() {
	overlay.setVisible(false);
	shader.setVisible(false);
    }
    
    public void loadDetailedSlicePopup(double x, double y, double w, double h, String color, String uText, String lText) {
	if(overlay != null && shader != null) {
	    if(popupCanvas != null) {
		overlay.remove(popupCanvas);
	    }
	    if(popupInar != null) {
		overlay.remove(popupInar);
	    }
	    int canvasWidth = ((int) (x+(2.0*w)));
	    int canvasHeight = ((int) (y+(2.0*h)));
	    popupCanvas = new DetailPopupCanvas(this,canvasWidth,canvasHeight);
	    popupCanvas.loadDetailedBar(x, y, w, h, color, uText, lText);
	    overlay.add(popupCanvas);
	    defocus();
	}
    }
    
    public void loadDetailedInarPopup(double cx, double cy, String shortYear, int curricularYear, int[] inar) {
	if(overlay != null && shader != null) {
	    if(popupCanvas != null) {
		overlay.remove(popupCanvas);
	    }
	    if(popupInar != null) {
		overlay.remove(popupInar);
	    }
	    int canvasWidth = ((int) (cx+500));
	    int canvasHeight = ((int) (cy+500));
	    popupInar = new DetailedInarPopup(this, canvasWidth, canvasHeight);
	    popupInar.loadDetailedInar(cx, cy, shortYear, curricularYear, inar);
	    overlay.add(popupInar);
	    defocus();
	}	    
    }
    
    private void initInarService() {
	inarService = (InarServiceAsync) GWT.create(InarService.class);
	ServiceDefTarget endpoint = (ServiceDefTarget) inarService;
	String moduleRelativeURL = GWT.getModuleBaseURL() + "InarService.gwt";
	endpoint.setServiceEntryPoint(moduleRelativeURL);
    }

    @Override
    public void onModuleLoad() {
	
	content = RootPanel.get("gwt_content");
	shader = RootPanel.get("gwt_shader");
	overlay = RootPanel.get("gwt_overlay");
	
        if (content != null && overlay != null && shader != null) {
            
            defocus();
            
            eyId = getArgs("eyId");
            dcpId = getArgs("dcpId");
            initInarService();
            
            rowOne = new HorizontalPanel();
            InarWidget inarWidget = new InarWidget(this,600,150,eyId,dcpId,inarService);
            rowOne.insert(inarWidget, 0);
            
            InarCaption inarCaption = new InarCaption(150);
            rowOne.insert(inarCaption,1);
            
            
            rowTwo = new HorizontalPanel();
            rowTwo.setSpacing(50);
            CompositeInarByCurricularYears inarByYear = new CompositeInarByCurricularYears(this, 400, 250, eyId, dcpId, inarService);
            rowTwo.insert(inarByYear, 0);
            CompositeAverageByCurricularYears averageByYear = new CompositeAverageByCurricularYears(this, 400, 250, eyId, dcpId, inarService);
            rowTwo.insert(averageByYear, 1);
            
            
            vPanel = new VerticalPanel();
            vPanel.setSpacing(20);
            vPanel.add(rowOne);
            vPanel.add(rowTwo);
            
            content.add(vPanel);
        }
    }

}