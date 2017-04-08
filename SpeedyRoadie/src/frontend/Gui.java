/*
 * SpeedyRoadie est le nom que l'on a donn� � notre Sokoban
 * Je vous souhaite un bon jeu!
 */
package frontend;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;
import backend.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de l'interface graphique.
 * Dans ce fichier, on retrouve toute l'implémentation graphique du projet
 * @author Louis Dhanis
 */
public class Gui extends JFrame implements ActionListener{
    private Game level;
    private JButton randGame;
    private JButton loadLevel;
    private JButton back;
    
    /**
     * Classe de l'interface graphique.
     * étend JFrame (conteneur principal)
     * @throws IOException
     */
    public Gui() throws IOException{
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.level = null;
        this.setContentPane(guiWelcome());
        this.setVisible(true);
        this.setLayout(new BorderLayout());
    }
    // Méthode Private car uniquement chargée par Gui()
    // Affiche le menu d'accueil
    private JPanel guiWelcome() throws IOException {
        JPanel panel = new JPanel();
        SpeedyBackground backgroundPanel = new SpeedyBackground("gameGraphics/welcomeBG.jpg");
        panel.setLayout(new BorderLayout());
        JPanel buttonContainer = new JPanel();
        setResizable(false);
        this.setSize(600,400);
        randGame = new JButton("Nouvelle partie aléatoire");
        randGame.addActionListener(this);
        loadLevel = new JButton("Charger un niveau");
        loadLevel.addActionListener(this);
        buttonContainer.add(randGame);
        buttonContainer.add(loadLevel);
        panel.add(backgroundPanel, BorderLayout.CENTER);
        panel.add(buttonContainer, BorderLayout.SOUTH);
        return panel;
    }
    // Méthode Private car uniquement chargée par Gui()
    // Affiche le JPanel du jeu
    // Prend en paramètre un niveau du jeu
    private JPanel guiGame(Game level){
        setResizable(false);
        final JPanel infos = new JPanel();
        infos.setLayout(new FlowLayout());
        final JLabel nbSteps = new JLabel("Nombre de pas: " + level.getNbSteps());
        infos.add(nbSteps);
        final JButton undo = new JButton("Annuler un mouvement");
        infos.add(undo);
        final JPanel gameContent = new JPanel();
        int height = level.getHeight()*50 + 50;
        int width = level.getWidth()*50;
        gameContent.setSize(height, width);
        gameContent.setLayout(new GridLayout(level.getHeight(), level.getWidth()));
        char[][] boardCode = level.getRepr();
        this.setSize(width, height);
        for(int i = 0; i < boardCode.length; i++){
            for(int j = 0; j < boardCode[i].length; j++){
                GuiElement elem = new GuiElement(boardCode[i][j], j ,i);
                elem.addActionListener(this);
                gameContent.add(elem);
            }
        }
        final JPanel guiGame = new JPanel();
        guiGame.setLayout(new BorderLayout());
        guiGame.add(infos, BorderLayout.NORTH);
        guiGame.add(gameContent, BorderLayout.CENTER);
        return guiGame;
    }
    // Méthode Private car uniquement chargée par Gui()
    // Affiche le menu de fin de niveau.
    private JPanel wowGG(){
        SpeedyBackground wowGG = new SpeedyBackground("gameGraphics/wowBG.jpg");
        
        return wowGG;
    }
    
    private JPanel levelLoader(String path){
        SpeedyBackground levelLoader = new SpeedyBackground("gameGraphic/fade.jpg");
        levelLoader.setLayout(new BorderLayout());
        File levelFolder = new File(path);
        File[] levelList = levelFolder.listFiles();
        SpeedyBackground buttonContainer = new SpeedyBackground("gameGraphics/fade.jpg");
        int height = Math.round(levelList.length / 2);
        buttonContainer.setLayout(new GridLayout(height , 2, 5, 5));
        back = new JButton("Retour");
        back.addActionListener(this);
        levelLoader.add(back, BorderLayout.NORTH);
        for (File levelList1 : levelList) {
            if (levelList1.isFile() && levelList1.getName().endsWith(".xsb")) {
                //XSB est le format par défaut pour les fichiers de jeu
                System.out.println(levelList1.getPath());
                GuiFile tempButton = new GuiFile(levelList1.getPath());
                buttonContainer.add(tempButton);
                tempButton.addActionListener(this);
                levelLoader.add(buttonContainer);
            }
        }
        JScrollPane scroller = new JScrollPane(buttonContainer);
        levelLoader.add(scroller, BorderLayout.CENTER);
        JLabel notice = new JLabel("Pour ajouter vos fichiers xsb, entrez-les dans le dossier "+ path, SwingConstants.CENTER);
        levelLoader.add(notice, BorderLayout.SOUTH);
        this.setResizable(true);
        return levelLoader;
    }

    /**
     * actionPerformed, gestionnaire des ActionEvent.
     * Permet d'établir la correspondance entre les boutons et ce qu'ils font.
     * Tous les boutons de l'interface graphique sont configurés dans cette partie
     * Méthode issue de l'interface ActionListener
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == randGame){
            try {
                System.out.println("TO BE IMPLEMENTED ASAP");
                this.level = new Game("gameMaps/level2.xsb");
                this.setContentPane(guiGame(this.level));
                this.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if(source == loadLevel){
            this.setContentPane(levelLoader("gameMaps/")); //Dossier des niveaux par défaut
            this.setVisible(true);
        }
        else if(source == back){
            try {
                this.setContentPane(guiWelcome());
            } catch (IOException ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setVisible(true);
        }
        else if(source.getClass() == GuiElement.class){
            GuiElement temp;
            temp = (GuiElement)source;
            System.out.println("Cliqué à "+temp.getPosX()+" "+temp.getPosY());
            this.level.movePlayerMouse(temp.getPosX(), temp.getPosY());
            this.level.printBoard();
            if(this.level.isGameWon()){
                this.setContentPane(wowGG());
                this.setVisible(true);
            }
            else{
                this.setContentPane(guiGame(this.level));
                this.setVisible(true);
            }
        }
        else if(source.getClass() == GuiFile.class) {
            GuiFile temp;
            temp = (GuiFile)source;
            try {
                this.level = new Game(temp.getPath());
            } catch (Exception ex) {
                infoBox("Une erreur est survenue avec ce fichier XSB, veuillez vérifier s'il comporte des caractères autres que [#,@,.,$,!]", "Erreur, fichier XSB incorrect");
            }
            this.setContentPane(guiGame(this.level));
            this.setVisible(true);
        }
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
