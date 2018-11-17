package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import static main.Catalogo.catalogo;

public class Juego extends JFrame implements ActionListener{

    private int total;
    private int nElfos;
    private int nHumanos;
    private int nOrcos;
    
    private final JPanel jpan;
    private int i=0;
    
    private final JButton b1 = new JButton("Generar mapa");
    private final JButton b2 = new JButton("Ingresar elfo");
    private final JButton b3 = new JButton("Ingresar Humano");
    private final JButton b4 = new JButton("Ingresar Orco");
    private final JButton b5 = new JButton("Atacar");
    
    private final JLabel matriz[][]=new JLabel[21][11];
    private final JLabel posX = new JLabel("posicion X:");
    private final JLabel posY = new JLabel("posicion Y:");
    private JLabel muestra[];
    
    private Icon iconoElfo;
    private Icon iconoHumano;
    private Icon iconoOrco;
    
    private final JLabel elfos = new JLabel("Elfos restantes: 10");
    private final JLabel humanos = new JLabel("Humanos restantes: 0");
    private final JLabel orco = new JLabel("Orcos restantes: 0");
    private final JLabel tropas[][]=new JLabel[20][10];
    
    private final JTextField textoX=new JTextField();
    private final JTextField textoY=new JTextField();
    
    private final javax.swing.border.Border border = LineBorder.createBlackLineBorder();
    
    public void mostrar(){
        
        Juego J=new Juego();
        J.setSize(1300,700);
        J.setVisible(true);
           
    }
    
    public Juego(){
        
        
        Container c=getContentPane();
        c.setLayout(null);
        this.getContentPane().setBackground(new Color(37,119,25));
        
        jpan=new JPanel();
        jpan.setBounds(0,0, 1075, 600);
        jpan.setOpaque(true);
        jpan.setBackground(new Color(37,119,255));
        
        b1.setBackground(Color.MAGENTA);
        b1.setBounds(1100, 100, 170, 30);
        b1.addActionListener(this);
        b2.setBackground(Color.ORANGE);
        b2.setBounds(1100, 400, 170, 30);
        b2.addActionListener(this);
        b3.setBackground(Color.ORANGE);
        b3.setBounds(1100, 450, 170, 30);
        b3.addActionListener(this);
        b4.setBackground(Color.ORANGE);
        b4.setBounds(1100, 500, 170, 30);
        b4.addActionListener(this);
        b5.setBackground(Color.RED);
        b5.setBounds(1100, 560, 170, 30);
        b5.addActionListener(this);
        
        posX.setBounds(1100, 330, 100, 20);
        posY.setBounds(1100, 370, 100, 20);
        elfos.setBounds(1100, 250, 200, 20);
        humanos.setBounds(1100, 275, 200, 20);
        orco.setBounds(1100, 300, 200, 20);
 
        textoX.setBounds(1170, 330, 20, 20);
        textoY.setBounds(1170, 370, 20, 20);
        
        c.add(jpan);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);
        c.add(b5);
        c.add(posX);
        c.add(posY);
        c.add(elfos);
        c.add(humanos);
        c.add(orco);
        c.add(textoX);
        c.add(textoY);

    }
    
    public void correr(){
        
        Timer timer=new Timer();
                
        TimerTask task;
            
        task = new TimerTask(){
            
            @Override
            public void run(){
                
                
                for(int j=0;j<10;j++){
                    
                    if(tropas[i][j]!=null){
                        
                        tropas[i+1][j]=tropas[i][j];
                        jpan.add(tropas[i+1][j]);
                        tropas[i+1][j].setBounds(50+(50*i), 50+(50*j), 50, 50);

                    
                    }
                    jpan.repaint();
                }    
                    
                if(i<18){
                    i++;
                } else {
                    timer.cancel();
                }
                
            }
        };
     
           timer.schedule(task, 1000, 500);
           
    }
    
    public void crearMuestras(){
        
        this.iconoElfo=catalogo.iconoElfo;
        this.iconoHumano=catalogo.iconoHumano;
        this.iconoOrco=catalogo.iconoOrco;
        
        this.nElfos=catalogo.nElfos;
        this.nHumanos=catalogo.nHumanos;
        this.nOrcos=catalogo.nOrcos;
        this.total=catalogo.ntropas;
        
        
        muestra=new JLabel[total];
        
        for(int k=0;k<total;k++){
            
            if(k<nElfos){
                
                muestra[k]=new JLabel();
                muestra[k].setIcon(iconoElfo);
                jpan.add(muestra[k]);
                muestra[k].setBounds(50+(5*k), 550, 50, 50);
                
            } else if(k>=nElfos && k<(nElfos+nHumanos)) {
             
                muestra[k]=new JLabel();
                muestra[k].setIcon(iconoHumano);
                jpan.add(muestra[k]);
                muestra[k].setBounds(400+(5*k), 550, 50, 50);
                
            } else if(k>=(nElfos+nHumanos) && k<(total)){
               
                muestra[k]=new JLabel();
                muestra[k].setIcon(iconoOrco);
                jpan.add(muestra[k]);
                muestra[k].setBounds(800+(5*k), 550, 50, 50);
                
            }
            
        }
        
        jpan.repaint();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==b1){
            
            for(int k=0;k<21;k++){
                
                for(int j=0;j<11;j++){
                    
                    if(k==0){
                    
                        matriz[k][j]=new JLabel(String.valueOf(j));
                        matriz[k][j].setBackground(Color.GRAY);
                        matriz[k][j].setOpaque(true);
                        matriz[k][j].setBorder(border);
                        matriz[k][j].setHorizontalAlignment(SwingConstants.CENTER);
                    
                    } else if(j==0){
                   
                        matriz[k][j]=new JLabel(String.valueOf(k)); 
                        matriz[k][j].setBackground(Color.GRAY);
                        matriz[k][j].setOpaque(true);
                        matriz[k][j].setBorder(border);
                        matriz[k][j].setHorizontalAlignment(SwingConstants.CENTER);
                    
                    } else {
                
                        matriz[k][j]=new JLabel("");
                        matriz[k][j].setBorder(border);
                    }
                
                jpan.add(matriz[k][j]);
                matriz[k][j].setBounds(50*k, 50*j, 50, 50);
                
                }           
            }
  
            crearMuestras();
            
        } else if(e.getSource()==b2){
            
            int x=Integer.parseInt(textoX.getText())-1;
            int y=Integer.parseInt(textoY.getText())-1;
            
            ImageIcon imgIcon5 = new ImageIcon(getClass().getResource("/Imagenes/"+"elfo2"+".png"));
            Image imgEscalada5 = imgIcon5.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
            Icon iconoEscalado5 = new ImageIcon(imgEscalada5);
            
            tropas[x][y]=new JLabel();
            tropas[x][y].setIcon(iconoEscalado5);
            
            jpan.add(tropas[x][y]);
            tropas[x][y].setBounds(50+(50*x),50+(50*y),50,50);
            jpan.remove(muestra[total]);
            total--;
            jpan.repaint();
            
        } else if(e.getSource()==b5){
            
            correr();
            
        }
        
    }
}