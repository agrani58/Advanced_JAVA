package Notepad;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


 class Myframe extends Frame  {  //creating class name is 'Myframe' extending from 'Frame' class 
    MenuBar bar;   
    Menu file,edit,font_size,theme;
    MenuItem New,open,save,copy,paste,cut;
    MenuItem DarkThemeMenuItem, lightThemeMenuItem;
    MenuItem size_8,size_12,size_16;
    TextArea textArea;
    String text;
  
   
     Myframe(){

        setTitle("Untitled - MeowPad");
            bar = new MenuBar();  //creating object of menubar and giving it reference

           file = new Menu("File");//creating object of menu as 'File' and giving it reference
            edit = new Menu("Edit");//creating object of menu as 'Edit' and giving it reference
            font_size=new Menu("Font Size");//creating object of menu as 'Font Size' and giving it reference
            theme = new Menu("Theme");//creating object of menu as 'Theme' and giving it reference
          
            
//creating object of MenuItem  and giving it reference,and Passing arguments 'label';
            New =new MenuItem("New");
            open =new MenuItem("Open");
            save =new MenuItem("Save");
   	    copy =new MenuItem("Copy");
            paste =new MenuItem("Paste");
            cut =new MenuItem("Cut");



// creating menuItem for font size menu
            size_8 =new MenuItem("8");
            size_12 =new MenuItem("12");
            size_16 =new MenuItem("16");

// creating menuItem for theme menu
            MenuItem darkThemeMenuItem = new MenuItem("Dark Theme"); 
            MenuItem lightThemeMenuItem = new MenuItem("Light Theme"); 

            file.add(New);
            file.add(open);
            file.add(save);
            edit.add(copy);
            edit.add(paste);
            edit.add(cut);

           font_size.add(size_8);
           font_size.add(size_12);
           font_size.add(size_16);

// adding MenuItem to theme Menu-------
           theme.add(darkThemeMenuItem);
           theme.add(lightThemeMenuItem);          

            textArea= new TextArea();//adding textarea
            // adding menus to bar
            bar.add(file);
            bar.add(edit);
            bar.add(font_size);
            bar.add(theme);

            setMenuBar(bar);            
            add(textArea);//adding text area

            // declaring some colors using rgb
            
            Color Dark = new Color(39,40,34);
            Color Light = new Color(255,255,255);

            // setting default foreground color of textArea and setting font
       
          

    //setting size and location and visibility
            setSize(1000,600);
            setLocationRelativeTo(null);
            setVisible(true);
        
        open.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                FileDialog dialog = new FileDialog(new Frame(),"Open",FileDialog.LOAD); //this will load  the fileDialog
                dialog.setVisible(true); //this will make dialog visible
                String path = dialog.getDirectory()+dialog.getFile(); //this will select the path of selected file and put it into 'path'
                setTitle(dialog.getFile()+" - Meowpad"); //this will set Title to selected file name 
                
                try {
                    FileInputStream fi = new FileInputStream(path); 
                    byte b[]=new byte[fi.available()];
                    fi.read(b);
                    String str = new String(b); //this will store b in str
                    textArea.setText(str); //this will display text in 'str' in textarea
                    fi.close(); //this will close fi
                    
                } catch (Exception e) {
                    
                    System.out.println("Something went Wrong :(");
                }
            }
        });

   New.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent event) {
            setTitle("Untitled - MeowPad");
            textArea.setText(" ");
        }
    });

        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                FileDialog dialog = new FileDialog(new Frame(),"Save ",FileDialog.SAVE);
                dialog.setVisible(true);
                String path = dialog.getDirectory()+dialog.getFile();
                setTitle(dialog.getFile()+"- MeowPad");
                
                try {

                 FileWriter fw = new FileWriter(path);
                    fw.write(textArea.getText());
                    fw.close();
                  
                    
                } catch (Exception e) {
                    
                    System.out.println("Something went Wrong :(");
                }
            }
        });

  cut.addActionListener(new ActionListener() {
	  public void actionPerformed(ActionEvent event) {
		  text=textArea.getSelectedText();
		  textArea.replaceRange("",textArea.getSelectionStart(),textArea.getSelectionEnd());
	  }
  });
      

  
      
        copy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                // Store the selected text in the 'text' variable
                text = textArea.getSelectedText();
            }
        });

        paste.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                // Insert the stored text at the current caret position
                textArea.insert(text, textArea.getCaretPosition());
            }
        });
// ------------adding font sizes -----------------------------------------------------
        
        size_8.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD,8)); //this will change the size of text in textarea to 8
            }
        });
        size_12.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD,12));
            }
        });
        size_16.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD,16));
            }
        });
       


        darkThemeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Set background and foreground colors to Dark
                textArea.setBackground(Dark);
                textArea.setForeground(Dark);
                textArea.setForeground(Color.white);
                textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
         
              
            }
        });
        lightThemeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Set background and foreground colors to Dark
                textArea.setBackground(Light);
                textArea.setForeground(Light);
                textArea.setForeground(Color.BLACK);
                textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
         
            }
        });
     
  
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent event){
                // setVisible(false);    //this will set visibility of frame to false ,so the frame become invisible
                System.exit(0);
            }
        });         
    }
 
}


public class Notepad{
    public static void main(String[] args) {
        new Myframe();//object
    }
}

