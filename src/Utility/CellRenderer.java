
package Utility;

  
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
  
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
  
  
public class CellRenderer extends JLabel implements ListCellRenderer {
    public CellRenderer(int tam) {
        setOpaque(true);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setFont(new Font("Roboto Condensed", Font.PLAIN,tam));
        setBorder(new EmptyBorder(10,10, 10, 10));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.decode("#F0F0F0")));
        setBackground(Color.decode("#BDBDBD"));
    }
  
    public Component getListCellRendererComponent(
                                       JList list,
                                       Object displayItem,
                                       int index,
                                       boolean isSelected,
                                       boolean cellHasFocus) {
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
  
 
        setText(displayItem.toString());
        return this;
    }
}
