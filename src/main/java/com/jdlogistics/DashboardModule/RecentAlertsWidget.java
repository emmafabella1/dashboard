package com.jdlogistics.DashboardModule;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * "Recent Alerts" card — a native SmartGWT ListGrid (sortable columns, alternating
 * row styling, resizable headers) rather than a plain HTML table, so it behaves and
 * feels like a real SmartGWT enterprise grid.
 */
public class RecentAlertsWidget extends VLayout {

    public RecentAlertsWidget() {
        setStyleName("jdCard");
        setPadding(12);
        setHeight100();
        setMembersMargin(6);

        HLayout header = new HLayout();
        header.setHeight(20);
        Label title = new Label("Recent Alerts");
        title.setStyleName("jdCardTitle");
        title.setWidth("*");
        header.addMember(title);

        addMember(header);
        addMember(buildGrid());
    }

    private ListGrid buildGrid() {
        ListGrid grid = new ListGrid();
        grid.setWidth100();
        grid.setHeight("*");
        grid.setShowAllRecords(true);
        grid.setCanSort(true);
        grid.setCanResizeFields(true);
        grid.setAlternateRecordStyles(true);
        grid.setShowRecordComponents(true);
        grid.setShowRecordComponentsByCell(true);

        ListGridField typeField = new ListGridField("type", "Type");
        typeField.setWidth(70);
        typeField.setCellFormatter((value, record, rowNum, colNum) -> {
            String v = value == null ? "" : value.toString();
            String styleClass = "jdAlertTypeInfo";
            if ("Critical".equalsIgnoreCase(v)) styleClass = "jdAlertTypeCritical";
            else if ("Warning".equalsIgnoreCase(v)) styleClass = "jdAlertTypeWarning";
            return "<span class='" + styleClass + "'>" + v + "</span>";
        });

        ListGridField messageField = new ListGridField("message", "Message");
        messageField.setWidth("*");

        ListGridField timeField = new ListGridField("time", "Time");
        timeField.setWidth(150);

        grid.setFields(typeField, messageField, timeField);
        grid.setData(buildRecords());
        return grid;
    }

    private ListGridRecord[] buildRecords() {
        return new ListGridRecord[] {
            record("Critical", "Shipment delayed - SHP123456", "May 16, 2024 10:32 AM"),
            record("Warning", "Tracking update failed - TRK987654", "May 16, 2024 09:15 AM"),
            record("Info", "Order ORD123789 created", "May 16, 2024 08:47 AM"),
            record("Critical", "Shipment exception - SHP123125", "May 16, 2024 07:50 AM"),
            record("Info", "Shipment delivered - SHP123120", "May 16, 2024 07:20 AM"),
        };
    }

    private ListGridRecord record(String type, String message, String time) {
        ListGridRecord r = new ListGridRecord();
        r.setAttribute("type", type);
        r.setAttribute("message", message);
        r.setAttribute("time", time);
        return r;
    }
}
