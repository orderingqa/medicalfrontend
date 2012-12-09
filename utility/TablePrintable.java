/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ls.jtsk.ui.utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author liushuai
 * 这个文件为打印jTable的关键文件
 */
public class TablePrintable implements Printable {

    private final JTable table;
    private final JTableHeader header;
    private final TableColumnModel colModel;
    private final int totalColWidth;
    private final JTable.PrintMode printMode;
    private final MessageFormat headerFormat;
    private final MessageFormat footerFormat;
    private int last = -1;
    private int row = 0;
    private int col = 0;
    private final Rectangle clip = new Rectangle(0, 0, 0, 0);
    private final Rectangle hclip = new Rectangle(0, 0, 0, 0);
    private final Rectangle tempRect = new Rectangle(0, 0, 0, 0);
    private static final int H_F_SPACE = 8;
    private static final float HEADER_FONT_SIZE = 12.0f;
    private static final float FOOTER_FONT_SIZE = 12.0f;
    private final Font headerFont;
    private final Font footerFont;

    public TablePrintable(JTable table, JTable.PrintMode printMode, MessageFormat headerFormat,
            MessageFormat footerFormat) {

        this.table = table;

        header = table.getTableHeader();
        colModel = table.getColumnModel();
        totalColWidth = colModel.getTotalColumnWidth();

        if (header != null) {
            // the header clip height can be set once since it's unchanging
            hclip.height = header.getHeight();
        }

        this.printMode = printMode;

        this.headerFormat = headerFormat;
        this.footerFormat = footerFormat;

        // derive the header and footer font from the table's font
        headerFont = table.getFont().deriveFont(Font.BOLD, HEADER_FONT_SIZE);
        footerFont = table.getFont().deriveFont(Font.PLAIN, FOOTER_FONT_SIZE);
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        // for easy access to these values
        final int imgWidth = (int) pageFormat.getImageableWidth();
        final int imgHeight = (int) pageFormat.getImageableHeight();

        if (imgWidth <= 0) {
            throw new PrinterException("Width of printable area is too small.");
        }

        // to pass the page number when formatting the header and footer
        // text
        Object[] pageNumber = new Object[] { Integer.valueOf(pageIndex + 1) };

        // fetch the formatted header text, if any
        String headerText = null;
        if (headerFormat != null) {
            headerText = headerFormat.format(pageNumber);
        }

        // fetch the formatted footer text, if any
        String footerText = null;
        if (footerFormat != null) {
            footerText = footerFormat.format(pageNumber);
        }

        // to store the bounds of the header and footer text
        Rectangle2D hRect = null;
        Rectangle2D fRect = null;

        // the amount of vertical space needed for the header and footer
        // text
        int headerTextSpace = 0;
        int footerTextSpace = 0;

        // the amount of vertical space available for printing the table
        int availableSpace = imgHeight;

        // if there's header text, find out how much space is needed for it
        // and subtract that from the available space
        if (headerText != null) {
            graphics.setFont(headerFont);
            int nbLines = headerText.split("\n").length;
            hRect = graphics.getFontMetrics().getStringBounds(headerText, graphics);


            hRect = new Rectangle2D.Double(hRect.getX(), Math.abs(hRect.getY()), hRect.getWidth(),
                    hRect.getHeight() * nbLines);

//            headerTextSpace = (int) Math.ceil(hRect.getHeight() * nbLines);
            headerTextSpace = (int) Math.ceil(hRect.getHeight()); // TODO 现在打印出来的效果是行数越多，表格往下平移的越多，我希望是个基本紧挨着的效果。故去掉行数相乘的代码。
            availableSpace -= headerTextSpace + H_F_SPACE;
        }

        // if there's footer text, find out how much space is needed for it
        // and subtract that from the available space
        if (footerText != null) {
            graphics.setFont(footerFont);
            fRect = graphics.getFontMetrics().getStringBounds(footerText, graphics);

            footerTextSpace = (int) Math.ceil(fRect.getHeight());
            availableSpace -= footerTextSpace + H_F_SPACE;
        }

        if (availableSpace <= 0) {
            throw new PrinterException("Height of printable area is too small.");
        }

        // depending on the print mode, we may need a scale factor to
        // fit the table's entire width on the page
        double sf = 1.0D;
        if (printMode == JTable.PrintMode.FIT_WIDTH && totalColWidth > imgWidth) {

            // if not, we would have thrown an acception previously
            assert imgWidth > 0;

            // it must be, according to the if-condition, since imgWidth > 0
            assert totalColWidth > 1;

            sf = (double) imgWidth / (double) totalColWidth;
        }

        // dictated by the previous two assertions
        assert sf > 0;

        // This is in a loop for two reasons:
        // First, it allows us to catch up in case we're called starting
        // with a non-zero pageIndex. Second, we know that we can be called
        // for the same page multiple times. The condition of this while
        // loop acts as a check, ensuring that we don't attempt to do the
        // calculations again when we are called subsequent times for the
        // same page.
        while (last < pageIndex) {
            // if we are finished all columns in all rows
            if (row >= table.getRowCount() && col == 0) {
                return NO_SUCH_PAGE;
            }

            // rather than multiplying every row and column by the scale
            // factor
            // in findNextClip, just pass a width and height that have
            // already
            // been divided by it
            int scaledWidth = (int) (imgWidth / sf);
            int scaledHeight = (int) ((availableSpace - hclip.height) / sf);

            // calculate the area of the table to be printed for this page
            findNextClip(scaledWidth, scaledHeight);

            last++;
        }

        // create a copy of the graphics so we don't affect the one given to
        // us
        Graphics2D g2d = (Graphics2D) graphics.create();

        // translate into the co-ordinate system of the pageFormat
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // to save and store the transform
        AffineTransform oldTrans;

        // if there's footer text, print it at the bottom of the imageable
        // area
        if (footerText != null) {
            oldTrans = g2d.getTransform();

            g2d.translate(0, imgHeight - footerTextSpace);

            String[] lines = footerText.split("\n");
            printText(g2d, lines, fRect, footerFont, imgWidth);

            g2d.setTransform(oldTrans);
        }

        // if there's header text, print it at the top of the imageable area
        // and then translate downwards
        if (headerText != null) {
            String[] lines = headerText.split("\n");
            printText(g2d, lines, hRect, headerFont, imgWidth);

            g2d.translate(0, headerTextSpace + H_F_SPACE);
        }

        // constrain the table output to the available space
        tempRect.x = 0;
        tempRect.y = 0;
        tempRect.width = imgWidth;
        tempRect.height = availableSpace;
        g2d.clip(tempRect);

        // if we have a scale factor, scale the graphics object to fit
        // the entire width
        if (sf != 1.0D) {
            g2d.scale(sf, sf);

            // otherwise, ensure that the current portion of the table is
            // centered horizontally
        } else {
            int diff = (imgWidth - clip.width) / 2;
            g2d.translate(diff, 0);
        }

        // store the old transform and clip for later restoration
        oldTrans = g2d.getTransform();
        Shape oldClip = g2d.getClip();

        // if there's a table header, print the current section and
        // then translate downwards
        if (header != null) {
            hclip.x = clip.x;
            hclip.width = clip.width;

            g2d.translate(-hclip.x, 0);
            g2d.clip(hclip);
            header.print(g2d);

            // restore the original transform and clip
            g2d.setTransform(oldTrans);
            g2d.setClip(oldClip);

            // translate downwards
            g2d.translate(0, hclip.height);
        }

        // print the current section of the table
        g2d.translate(-clip.x, -clip.y);
        g2d.clip(clip);
        table.print(g2d);

        // restore the original transform and clip
        g2d.setTransform(oldTrans);
        g2d.setClip(oldClip);

        // draw a box around the table
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, clip.width, hclip.height + clip.height);

        // dispose the graphics copy
        g2d.dispose();

        return PAGE_EXISTS;
    }

    private void printText(Graphics2D g2d, String[] lines, Rectangle2D rect, Font font, int imgWidth) {

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);

        for (int i = 0; i < lines.length; i++) {
            int tx;
            tx = 0; // 这个强制赋值是为了保证每次打印头和尾时都采用自左到右打印次序。
            // 打印的第一行是标题，故而要居中。
//            if (i == 0) {
                // if the text is small enough to fit, center it
                if (rect.getWidth() < imgWidth) {
                    tx = (int) (imgWidth / 2 - g2d.getFontMetrics().getStringBounds(lines[i], g2d).getWidth() / 2);

                    // otherwise, if the table is LTR, ensure the left side of
                    // the text shows; the right can be clipped
                } else if (table.getComponentOrientation().isLeftToRight()) {
                    tx = 0;
                    // otherwise, ensure the right side of the text shows
                } else {
                    tx = -(int) (Math.ceil(rect.getWidth()) - imgWidth);
                }
//            }
            int ty = (int) Math.ceil(Math.abs(rect.getY() + i * rect.getHeight() / lines.length));
            g2d.drawString(lines[i], tx, ty);
        }
    }

    private void findNextClip(int pw, int ph) {
        final boolean ltr = table.getComponentOrientation().isLeftToRight();

        // if we're ready to start a new set of rows
        if (col == 0) {
            if (ltr) {
                // adjust clip to the left of the first column
                clip.x = 0;
            } else {
                // adjust clip to the right of the first column
                clip.x = totalColWidth;
            }

            // adjust clip to the top of the next set of rows
            clip.y += clip.height;

            // adjust clip width and height to be zero
            clip.width = 0;
            clip.height = 0;

            // fit as many rows as possible, and at least one
            int rowCount = table.getRowCount();
            int rowHeight = table.getRowHeight(row);
            do {
                clip.height += rowHeight;

                if (++row >= rowCount) {
                    break;
                }

                rowHeight = table.getRowHeight(row);
            } while (clip.height + rowHeight <= ph);
        }

        // we can short-circuit for JTable.PrintMode.FIT_WIDTH since
        // we'll always fit all columns on the page
        if (printMode == JTable.PrintMode.FIT_WIDTH) {
            clip.x = 0;
            clip.width = totalColWidth;
            return;
        }

        if (ltr) {
            // adjust clip to the left of the next set of columns
            clip.x += clip.width;
        }

        // adjust clip width to be zero
        clip.width = 0;

        // fit as many columns as possible, and at least one
        int colCount = table.getColumnCount();
        int colWidth = colModel.getColumn(col).getWidth();
        do {
            clip.width += colWidth;
            if (!ltr) {
                clip.x -= colWidth;
            }

            if (++col >= colCount) {
                // reset col to 0 to indicate we're finished all columns
                col = 0;

                break;
            }

            colWidth = colModel.getColumn(col).getWidth();
        } while (clip.width + colWidth <= pw);

    }
}
