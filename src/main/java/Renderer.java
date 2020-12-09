public class Renderer implements Runnable {

    private Window window;

    public Renderer(Window window){
        this.window = window;
    }

    public void run() {
        while(true){
            this.window.repaint();
        }
    }
}
