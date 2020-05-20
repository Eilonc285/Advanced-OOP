
public class FinishingLine {
	private int index;

	public FinishingLine() {
		index = 1;
	}

	public synchronized void arrive(Horse h) {
		if (index == 1) {
			System.out.println("*******************************");
			System.out.printf("====> %s finished first!\n", h.getId());
			System.out.println("*******************************");
			index++;
		} else if (index == 2) {
			System.out.printf("====> %s finished on 2-nd place\n", h.getId());
			index++;
		} else if (index == 3) {
			System.out.printf("====> %s finished on 3-rd place\n", h.getId());
			index++;
		} else {
			System.out.printf("====> %s finished on %d-th place\n", h.getId(), index);
			index++;
		}
	}
}
