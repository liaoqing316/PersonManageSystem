package Star;
import javax.swing.*;
import java.awt.*;

public class GetScrHeiAndWid extends JFrame{
    Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
    Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
    int screenWidth = screenSize.width/2; // 获取屏幕的宽
    int screenHeight = screenSize.height/2; // 获取屏幕的高
    int height = this.getHeight();
    int width = this.getWidth();
    public int Center_Width(){
        return screenWidth/2-width/2;
    }

    public int Center_Height(){
        return screenHeight/2-height/2;
    }
}
