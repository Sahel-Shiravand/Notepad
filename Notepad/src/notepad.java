import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class notepad extends JFrame implements ActionListener {
    private JFileChooser fc = new JFileChooser();
    private JTextArea dat;
    private JMenuBar menu = new JMenuBar();
    private JMenu file, edit, format, font, bg, fg, help, view;
    private JMenu zoom;

    private JMenuItem nu, open, save, saveas, exit, copy, paste, clear, sall, print, printpreview, about, b8, b14, b18, b25, b30, bb, br, bge, fb, fr, fge, zoom_in, zoom_out;
    final static String LOOKANDFEEL = null;

    public notepad() {
        setLocation(200, 200);
        setTitle("notepad 1.00");
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        file = new JMenu("file");
        menu.add(file);
        /////////////////////////
        nu = new JMenuItem("new");
        file.add(nu);
        nu.addActionListener(this);
        /////////////////////////
        open = new JMenuItem("open");
        file.add(open);
        open.addActionListener(this);
        /////////////////////////
        save = new JMenuItem("save");
        file.add(save);
        save.addActionListener(this);
        /////////////////////////
        saveas = new JMenuItem("save as");
        file.add(saveas);
        saveas.addActionListener(this);
        file.add(new JSeparator());
        /////////////////////////
        print = new JMenuItem("print");
        file.add(print);
        print.addActionListener(this);
        ////////////////////////
        printpreview = new JMenuItem("print preview");
        file.add(printpreview);
        printpreview.addActionListener(this);
        file.add(new JSeparator());
        ////////////////////////
        exit = new JMenuItem("exit");
        file.add(exit);
        exit.addActionListener(this);
        /////////////////////////
        edit = new JMenu("edit");
        menu.add(edit);
        ////////////////////////
        format = new JMenu("format");
        menu.add(format);
        ////////////////////////
        help = new JMenu("help");
        menu.add(help);
        ////////////////////////
        view = new JMenu("View");
        menu.add(view);
        ///////////////////////
        zoom = new JMenu("Zoom");
        view.add(zoom);
        zoom.addActionListener(this);
        ///////////////////////
        zoom_in = new JMenuItem("Zoom In");
        zoom.add(zoom_in);
        zoom_in.addActionListener(this);
        //////////////////////
        zoom_out = new JMenuItem("Zoom Out");
        zoom.add(zoom_out);
        zoom_out.addActionListener(this);
        ///////////////////////
        sall = new JMenuItem("select all");
        edit.add(sall);
        sall.addActionListener(this);
        ///////////////////////
        copy = new JMenuItem("copy");
        edit.add(copy);
        copy.addActionListener(this);
        //////////////////////
        paste = new JMenuItem("paste");
        edit.add(paste);
        paste.addActionListener(this);
        /////////////////////
        clear = new JMenuItem("clear");
        edit.add(clear);
        clear.addActionListener(this);
        /////////////////////
        font = new JMenu("font");
        format.add(font);
        font.addActionListener(this);
        ////////////////////
        b8 = new JMenuItem("8");
        font.add(b8);
        b8.addActionListener(this);
        ///////////////////
        b14 = new JMenuItem("14");
        font.add(b14);
        b14.addActionListener(this);
        ///////////////////
        b18 = new JMenuItem("18");
        font.add(b18);
        b18.addActionListener(this);
        ///////////////////
        b25 = new JMenuItem("25");
        font.add(b25);
        b25.addActionListener(this);
        ///////////////////
        b30 = new JMenuItem("30");
        font.add(b25);
        b25.addActionListener(this);
        ///////////////////
        bg = new JMenu("background color");
        format.add(bg);
        //////////////////
        bb = new JMenuItem("blue");
        bg.add(bb);
        bb.addActionListener(this);
        //////////////////
        br = new JMenuItem("red");
        bg.add(br);
        br.addActionListener(this);
        /////////////////
        bge = new JMenuItem("green");
        bb.add(bge);
        bge.addActionListener(this);
        ////////////////
        fg = new JMenu("foreGround color");
        format.add(fg);
        ////////////////
        fb = new JMenuItem("blue");
        fg.add(fb);
        fb.addActionListener(this);
        ////////////////
        fr = new JMenuItem("Red");
        fr.addActionListener(this);
        fg.add(fr);
        ///////////////
        fge = new JMenuItem("Green");
        fge.addActionListener(this);
        fg.add(fge);
        //////////////
        about = new JMenuItem("about");
        help.add(about);
        about.addActionListener(this);
        /////////////
        dat = new JTextArea(8, 8);
        JPanel spane = new JPanel(new GridLayout(0, 1));
        spane.add(dat);
        /////////////////////
        JScrollPane sp = new JScrollPane(spane);
        /////////////////////
        JPanel mapane = new JPanel((new GridLayout(0, 1)));
        getContentPane().add(sp);
        setJMenuBar(menu);
    }

    ;

    ////////////////////
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nu) {
            int nuask = JOptionPane.showConfirmDialog(notepad.this, "Are you sure? This will make a erease any unsaved documents!");
            if (nuask == JOptionPane.YES_OPTION) {
                dat.setText("");
            }
        }
        if (e.getSource() == open) {
            int returnVal = fc.showOpenDialog(notepad.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    BufferedReader inputStream = new BufferedReader(new FileReader(file.getPath()));
                    String inputLine;
                    dat.setText("");
                    setTitle("notepad-" + file.getName());
                    while ((inputLine = inputStream.readLine()) != null) {
                        dat.append(inputLine + "\n");
                    }
                } catch (FileNotFoundException ioe) {
                    JOptionPane.showMessageDialog(null, "Sorry,File Not Found", "", JOptionPane.WARNING_MESSAGE);
                } catch (IOException ioe) {
                    System.out.println(file.getAbsoluteFile());
                }
            }
        }
        ////***///
        if (e.getSource() == save) {
            File file = fc.getSelectedFile();
            try {
                FileWriter outputStream = new FileWriter(file.getPath());
                outputStream.close();
                setTitle("text editor-" + file.getName());
            } catch (IOException ioe) {
                System.out.println("IOException");
            }
        }
        if (e.getSource() == saveas) {
            int returnVal = fc.showSaveDialog(notepad.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fc.getSelectedFile();
                    FileWriter outputStream = new FileWriter(file.getPath() + ".txt");
                    outputStream.write(dat.getText());
                    outputStream.close();


                } catch (IOException ioe) {
                    System.out.println("IOExeption");
                }
            }
        }
/////////*****///////
        if (e.getSource() == exit) {
            int ask = JOptionPane.showConfirmDialog(notepad.this, "Are you sure you want to exit " + getTitle() + " ?");
            if (ask == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }


//		if(e.getSource()==sall){
//
//		}
        if (e.getSource() == copy) {
            dat.copy();
        }
        if (e.getSource() == paste) {
            dat.paste();
        }
        if (e.getSource() == clear) {
            int cask = JOptionPane.showConfirmDialog(notepad.this, "Are you sure you want to clear the document?");
            if (cask == JOptionPane.YES_OPTION) {
                dat.setText("");
                setTitle("notepad");

            }
        }
        if (e.getSource() == b8) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 8));

        }
        if (e.getSource() == b14) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 14));

        }
        if (e.getSource() == b18) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 18));

        }
        if (e.getSource() == b25) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 25));

        }
        if (e.getSource() == b30) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 30));

        }
        if (e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "Created by Sahel Shiravand in 2021 \n\nErrors:\n--------------------------------------------------------\n1.Button/Actions - print preview\n2.Button/Actions - print\n3.Button/Actions - select all\n4.Saving color", "", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == bb) {
            dat.setBackground(Color.BLUE);
        }
        if (e.getSource() == br) {
            dat.setBackground(Color.RED);
        }
        if (e.getSource() == bge) {
            dat.setBackground(Color.GREEN);
        }
        if (e.getSource() == fb) {
            dat.setForeground(Color.BLUE);
        }
        if (e.getSource() == fr) {
            dat.setForeground(Color.RED);
        }
        if (e.getSource() == fge) {
            dat.setForeground(Color.GREEN);
        }
        if (e.getSource() == zoom_in) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 25));
        }
        if (e.getSource() == zoom_out) {
            dat.setFont(new Font(dat.getFont().getName(), dat.getFont().getStyle(), 8));
        }

    }
}