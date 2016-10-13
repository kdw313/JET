package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import net.miginfocom.swing.MigLayout;
import objects.BlockType;
import objects.Renderer2D;
import objects.VObject;

public class Renderer2DPanel extends BlockPanel {

    private VObject obj;
    private Renderer2D rend;

    private JPanel panel;
    private JTextField imgLink;

    public Renderer2DPanel(VObject obj) {
        super(BlockType.RENDERER_2D);
        panel = new JPanel();
        panel.setLayout(new MigLayout("insets 0, wrap 3",
                "0[][]0[]0",
                "0[grow, fill]0"));
        this.obj = obj;
        rend = (Renderer2D) obj.getBlockByType(BlockType.RENDERER_2D);
        imgLink = new JTextField(200);
        imgLink.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    requestFocusInWindow();
                    revalidate();
                }
            }
        });
        imgLink.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent e) {
                rend.setImg(imgLink.getText());
                obj.setBlockByType(BlockType.RENDERER_2D, rend);
                JetMenu.main.objH.setVObject(obj.getName(), obj);
            }
        });
        imgLink.setText(rend.getImg());
        panel.add(new JLabel("File:"));
        panel.add(imgLink);
        JButton browse = new JButton("Browse");
        browse.addActionListener(new BrowserListener());
        panel.add(browse);
        add(panel, "newline");
    }

    private class BrowserListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
        }

    }

    private class ImageFileFilter extends FileFilter {

        @Override
        public boolean accept(File arg0) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public String getDescription() {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
