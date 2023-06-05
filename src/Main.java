import controller.ProgramController;
import dao.SnakeDao;
import view.SwingView;

public class Main {
    public static void main(String[] args) {
        ProgramController controller = new ProgramController(new SwingView(), new SnakeDao());
        controller.start();
    }
}