package com.sap.ibso.eservices.storefront.util;

import org.jfree.chart.renderer.category.StackedBarRenderer;

import java.awt.*;

public class CategoryItemRenderer extends StackedBarRenderer {
    @Override
    public Paint getItemPaint(int row, int column) {
        if (row == 0) {
            return new Color(89, 168, 110);   // Color for stack 1
        } else {
            return new Color(145, 129, 64); // Color for stack 2
        }
    }

}
