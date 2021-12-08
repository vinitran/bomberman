 package Entities.StaticEntity.Item;

 import Entities.StaticEntity.StaticEntity;
 import Main.GamePanel;

 public class Item {
     GamePanel gamePanel;
     public Item(GamePanel gamePanel) {
         this.gamePanel = gamePanel;
     }

     public void setObject() {
         gamePanel.item[0] = new SpeedItem(1, 5, gamePanel);
         gamePanel.item[1] = new Flash(5, 1, gamePanel);
         gamePanel.item[2] = new Flash(5, 10, gamePanel);
     }



 }