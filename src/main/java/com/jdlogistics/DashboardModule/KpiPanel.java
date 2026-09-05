package com.jdlogistics.DashboardModule;

import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Row of KPI stat tiles shown at the top of the Operations Dashboard:
 * Total Shipments / In Transit / Delivered / Delayed / Open Orders.
 * Mirrors the "KPI Panel" widget from the dashboard wireframe.
 */
public class KpiPanel extends HLayout {

    public KpiPanel() {
        setWidth100();
        setHeight(90);
        setMembersMargin(12);

        addMember(buildTile("Total Shipments", "12,345", "+5.2% vs yesterday", true));
        addMember(buildTile("In Transit", "5,678", "+3.1% vs yesterday", true));
        addMember(buildTile("Delivered", "6,512", "+8.4% vs yesterday", true));
        addMember(buildTile("Delayed", "155", "-2.3% vs yesterday", false));
        addMember(buildTile("Open Orders", "2,340", "+1.7% vs yesterday", true));
    }

    private VLayout buildTile(String title, String value, String delta, boolean positive) {
        VLayout tile = new VLayout();
        tile.setStyleName("jdCard");
        tile.setWidth("20%");
        tile.setHeight100();
        tile.setPadding(12);
        tile.setMembersMargin(4);
        tile.setOverflow(Overflow.HIDDEN);

        Label labelTitle = new Label(title);
        labelTitle.setStyleName("jdKpiLabel");
        labelTitle.setHeight(16);
        labelTitle.setAutoWidth();

        Label labelValue = new Label(value);
        labelValue.setStyleName("jdKpiValue");
        labelValue.setHeight(30);
        labelValue.setAutoWidth();

        Label labelDelta = new Label(delta);
        labelDelta.setStyleName(positive ? "jdKpiDeltaPos" : "jdKpiDeltaNeg");
        labelDelta.setHeight(16);
        labelDelta.setAutoWidth();
        labelDelta.setValign(VerticalAlignment.BOTTOM);

        tile.addMember(labelTitle);
        tile.addMember(labelValue);
        tile.addMember(labelDelta);
        return tile;
    }
}
