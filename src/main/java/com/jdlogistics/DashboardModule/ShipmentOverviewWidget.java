package com.jdlogistics.DashboardModule;

import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * "Shipment Overview" card: a small multi-series line chart (Created / In Transit / Delivered)
 * plotted over the last 7 days, plus a title bar and legend — matching the wireframe.
 *
 * The chart itself is rendered as inline SVG inside an HTMLFlow. This keeps the widget
 * dependency-free (no separate charting library needed) while still living inside a
 * native SmartGWT VLayout card alongside the rest of the dashboard.
 */
public class ShipmentOverviewWidget extends VLayout {

    // Sample series data (May 10 - May 16), values in thousands
    private static final String[] DAYS = {"May 10", "May 11", "May 12", "May 13", "May 14", "May 15", "May 16"};
    private static final int[] CREATED   = {5800, 6800, 6600, 7400, 6300, 7000, 7900};
    private static final int[] IN_TRANSIT = {3600, 3900, 4000, 3900, 4400, 4200, 4700};
    private static final int[] DELIVERED  = {1400, 1700, 1600, 1550, 1650, 1600, 1900};

    public ShipmentOverviewWidget() {
        setStyleName("jdCard");
        setPadding(12);
        setHeight100();
        setMembersMargin(6);

        HLayout header = new HLayout();
        header.setHeight(20);
        Label title = new Label("Shipment Overview");
        title.setStyleName("jdCardTitle");
        title.setWidth("*");
        header.addMember(title);

        HTMLFlow chart = new HTMLFlow(buildChartSvg());
        chart.setWidth100();
        chart.setHeight("*");

        addMember(header);
        addMember(chart);
    }

    private String buildChartSvg() {
        int w = 620, h = 260;
        int padLeft = 40, padBottom = 24, padTop = 10, padRight = 10;
        int chartW = w - padLeft - padRight;
        int chartH = h - padTop - padBottom;
        int maxVal = 8000;

        StringBuilder svg = new StringBuilder();
        svg.append("<svg width='100%' height='230' viewBox='0 0 ").append(w).append(" ").append(h)
           .append("' xmlns='http://www.w3.org/2000/svg' style='font-family:Arial,sans-serif;font-size:10px;'>");

        // Horizontal gridlines + y-axis labels (0, 2K, 4K, 6K, 8K)
        for (int i = 0; i <= 4; i++) {
            int y = padTop + chartH - (chartH * i / 4);
            svg.append("<line x1='").append(padLeft).append("' y1='").append(y)
               .append("' x2='").append(w - padRight).append("' y2='").append(y)
               .append("' stroke='#e2e8f0' stroke-width='1'/>");
            svg.append("<text x='").append(padLeft - 8).append("' y='").append(y + 3)
               .append("' text-anchor='end' fill='#94a3b8'>").append(i == 0 ? "0" : (i * 2) + "K").append("</text>");
        }

        // X-axis labels
        for (int i = 0; i < DAYS.length; i++) {
            int x = padLeft + (chartW * i / (DAYS.length - 1));
            svg.append("<text x='").append(x).append("' y='").append(h - 6)
               .append("' text-anchor='middle' fill='#94a3b8'>").append(DAYS[i]).append("</text>");
        }

        appendSeries(svg, CREATED, "#2563eb", padLeft, padTop, chartW, chartH, maxVal);
        appendSeries(svg, IN_TRANSIT, "#16a34a", padLeft, padTop, chartW, chartH, maxVal);
        appendSeries(svg, DELIVERED, "#7c3aed", padLeft, padTop, chartW, chartH, maxVal);

        svg.append("</svg>");

        // Legend below the chart
        svg.append("<div style='text-align:center;font-size:12px;color:#334155;padding-top:2px;'>")
           .append(legendDot("#2563eb")).append(" Created &nbsp;&nbsp; ")
           .append(legendDot("#16a34a")).append(" In Transit &nbsp;&nbsp; ")
           .append(legendDot("#7c3aed")).append(" Delivered")
           .append("</div>");

        return svg.toString();
    }

    private String legendDot(String color) {
        return "<span style='display:inline-block;width:9px;height:9px;border-radius:50%;background:"
                + color + ";margin-right:4px;'></span>";
    }

    private void appendSeries(StringBuilder svg, int[] values, String color,
                               int padLeft, int padTop, int chartW, int chartH, int maxVal) {
        StringBuilder points = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int x = padLeft + (chartW * i / (values.length - 1));
            int y = padTop + chartH - (chartH * values[i] / maxVal);
            points.append(x).append(",").append(y);
            if (i < values.length - 1) points.append(" ");
        }
        svg.append("<polyline fill='none' stroke='").append(color)
           .append("' stroke-width='2' points='").append(points).append("'/>");

        for (int i = 0; i < values.length; i++) {
            int x = padLeft + (chartW * i / (values.length - 1));
            int y = padTop + chartH - (chartH * values[i] / maxVal);
            svg.append("<circle cx='").append(x).append("' cy='").append(y)
               .append("' r='3' fill='").append(color).append("'/>");
        }
    }
}
