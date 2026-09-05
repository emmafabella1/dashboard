package com.jdlogistics.DashboardModule;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * "Quick Actions" card: a row of native SmartGWT IButtons
 * (Create Shipment / Search Tracking / View Shipments / View Orders).
 */
public class QuickActionsWidget extends VLayout {

    public QuickActionsWidget() {
        setStyleName("jdCard");
        setPadding(12);
        setHeight(90);
        setMembersMargin(8);

        Label title = new Label("Quick Actions");
        title.setStyleName("jdCardTitle");
        title.setHeight(20);

        HLayout buttonRow = new HLayout();
        buttonRow.setHeight(40);
        buttonRow.setMembersMargin(10);

        buttonRow.addMember(makeButton("+ Create Shipment"));
        buttonRow.addMember(makeButton("\uD83D\uDD0D Search Tracking"));
        buttonRow.addMember(makeButton("\u2261 View Shipments"));
        buttonRow.addMember(makeButton("\uD83D\uDCC4 View Orders"));

        addMember(title);
        addMember(buttonRow);
    }

    private IButton makeButton(String text) {
        IButton button = new IButton(text);
        button.setWidth(160);
        button.setHeight(36);
        button.addClickHandler((ClickEvent event) -> Window.alert(text + " clicked"));
        return button;
    }
}
