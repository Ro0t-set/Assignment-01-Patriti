package mvc_01_web;

public class AppMain {
  static public void main(String[] args) throws Exception {
	  
	MyModel model = new MyModel();
    MyView view = new MyView(model);
	new MySocketIoServer(model);
    MyInputUI inputUI = new MyInputUI();
	MyController controller = new MyController(model);
	inputUI.addObserver(controller);
	view.display();
	inputUI.display();
  }	
  
}
