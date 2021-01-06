package com.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame
{
    private JPanel mainPanel;
    private JButton FileButton;
    private JProgressBar progressBar1;

    public Window()
    {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 560;
        int height = 380;
        setSize(width, height);
        setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
        setResizable(false);

        setContentPane(mainPanel);
        setVisible(true);


        FileButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showOpenDialog(null);
            }
        });
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
