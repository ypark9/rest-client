package org.wisdom.tool.gui.req;

import org.wisdom.tool.constant.RESTConst;
import org.wisdom.tool.model.CadInputType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReqInputTabPanel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 5120996065049850894L;

    private JLabel lbProjectName = null;
    private JLabel lbInputType = null;

    private JLabel lbDocName = null;

    private JLabel lblCharset = null;

    private JPanel pnlSouth = null;

    private JPanel pnlCenter = null;

    private JPanel pnlNorth = null;

    private JFileChooser fc = null;

    private JPopupMenu pm = null;

    private JMenuItem miFmt = null;

    private JMenuItem miCpy = null;

    private JMenuItem miPst = null;

    private JMenuItem miClr = null;
    private MouseAdapter ma = new MouseAdapter()
    {
        private void popup(MouseEvent e)
        {
//            txtAraBody.requestFocus();
//            miPst.setEnabled(true);
//            if (!txtAraBody.isEnabled() || StringUtils.isBlank(txtAraBody.getText()))
//            {
//                miFmt.setEnabled(false);
//                miCpy.setEnabled(false);
//                miClr.setEnabled(false);
//            }
//            else
//            {
//                miFmt.setEnabled(true);
//                miCpy.setEnabled(true);
//                miClr.setEnabled(true);
//            }
//
//            if (e.isPopupTrigger())
//            {
//                pm.show(e.getComponent(), e.getX(), e.getY());
//            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            this.popup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            this.popup(e);
        }
    };
    private JComboBox<CadInputType> cbInputType;
    private JTextField txtFldProjectName;
    private JTextField txtFldDocName;

    public ReqInputTabPanel()
    {
        this.init();
    }

    /**
     *
     * @Title: init
     * @Description: Component Initialization
     * @param
     * @return void
     * @throws
     */
    private void init()
    {
        this.setLayout(new BorderLayout(RESTConst.BORDER_WIDTH, 0));

        lbInputType = new JLabel("Input Type:");
        lbProjectName = new JLabel("Project Name:");
        lbDocName = new JLabel("Document Name:");

        cbInputType = new JComboBox<CadInputType>(CadInputType.values());
        txtFldProjectName = new JTextField(RESTConst.FIELD_SIZE);
        txtFldDocName = new JTextField(RESTConst.FIELD_SIZE);

        JPanel wcPanel = new JPanel(new BorderLayout(RESTConst.BORDER_WIDTH, RESTConst.BORDER_WIDTH));
        JPanel wcProjectPanel = new JPanel();
        JPanel wcDocumentPanel = new JPanel();
        JPanel inputTypePanel = new JPanel();

        inputTypePanel.add(lbInputType);
        inputTypePanel.add(cbInputType);
        wcProjectPanel.add(lbProjectName);
        wcProjectPanel.add(txtFldProjectName);
        wcDocumentPanel.add(lbDocName);
        wcDocumentPanel.add(txtFldDocName);

        wcPanel.add(wcProjectPanel, BorderLayout.NORTH);
        wcPanel.add(wcDocumentPanel, BorderLayout.CENTER);

        this.add(inputTypePanel, BorderLayout.NORTH);
        this.add(wcPanel, BorderLayout.CENTER);

        miFmt = new JMenuItem(RESTConst.FORMAT);
        miFmt.setName(RESTConst.FORMAT);
        miFmt.addActionListener(this);

        miCpy = new JMenuItem(RESTConst.COPY);
        miCpy.setName(RESTConst.COPY);
        miCpy.addActionListener(this);

        miClr = new JMenuItem(RESTConst.CLEAR);
        miClr.setName(RESTConst.CLEAR);
        miClr.addActionListener(this);

        miPst = new JMenuItem(RESTConst.PASTE);
        miPst.setName(RESTConst.PASTE);
        miPst.addActionListener(this);

        pm = new JPopupMenu();
        pm.add(miCpy);
        pm.addSeparator();
        pm.add(miPst);
        pm.addSeparator();
        pm.add(miFmt);
        pm.addSeparator();
        pm.add(miClr);

        fc = new JFileChooser();
    }

    private void bdyPerformed(Object src)
    {
        if (!(src instanceof JComboBox))
        {
            return;
        }

//        @SuppressWarnings("unchecked")
//        JComboBox<String> cb = (JComboBox<String>) src;
//        String bodyType = (String) cb.getSelectedItem();
//        if (BodyType.STRING.getType().equals(bodyType))
//        {
//            pnlSouth.setVisible(false);
//            txtAraBody.setEnabled(true);
//            txtAraBody.setBackground(Color.white);
//            txtAraBody.requestFocus();
//        }
//        else
//        {
//            pnlSouth.setVisible(true);
//            txtFldPath.requestFocus();
//            txtAraBody.setEnabled(false);
//            txtAraBody.setBackground(UIUtil.lightGray());
//        }
    }

    private void btnLoadPerformed(Object src)
    {
//        if (!(src instanceof JButton))
//        {
//            return;
//        }
//        JButton btn = (JButton) src;
//        if (!RESTConst.BROWSE.equals(btn.getName()))
//        {
//            return;
//        }
//
//        String content = UIUtil.openFile(this, fc);
//        if (StringUtils.isEmpty(content))
//        {
//            return;
//        }
//
//        txtAraBody.setText(content);
    }

    private void menuPerformed(Object src)
    {
        if (!(src instanceof JMenuItem))
        {
            return;
        }

//        JMenuItem item = (JMenuItem) (src);
//        if (RESTConst.FORMAT.equals(item.getName()))
//        {
//            String body = RESTUtil.format(txtAraBody.getText());
//            txtAraBody.setText(body);
//            return;
//        }
//
//        if (RESTConst.COPY.equals(item.getName()))
//        {
//            StringSelection ss = null;
//            String seltxt = txtAraBody.getSelectedText();
//            if (StringUtils.isNotBlank(seltxt))
//            {
//                ss = new StringSelection(seltxt);
//            }
//            else
//            {
//                ss = new StringSelection(txtAraBody.getText());
//            }
//
//            Toolkit.getDefaultToolkit()
//                    .getSystemClipboard()
//                    .setContents(ss, null);
//
//            return;
//        }
//
//        if (RESTConst.PASTE.equals(item.getName()))
//        {
//            txtAraBody.paste();
//            return;
//        }
//
//        if (RESTConst.CLEAR.equals(item.getName()))
//        {
//            txtAraBody.setText(StringUtils.EMPTY);
//            return;
//        }
    }

    public void actionPerformed(ActionEvent e)
    {
//        this.bdyPerformed(e.getSource());
//        this.btnLoadPerformed(e.getSource());
//        this.menuPerformed(e.getSource());
    }

}
