package main.java.logic;

import java.util.ArrayList;

public class TestOut {

	private int num;
	private String str;

	public TestOut(int num, String str) {
		this.num = num;
		this.str = str;
	}

	public int getNum() {
		return num;
	}

	public String getStr() {
		return str;
	}

	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object != null && object instanceof TestOut) {
			result = this.getNum() == ((TestOut) object).getNum() && this.getStr().equals(((TestOut) object).getStr());
		}

		return result;

	}

	public static void main(String[] args) {

		TestOut out = new TestOut(1, "2");
		ArrayList<TestOut> list = new ArrayList<TestOut>();
		list.add(out);
		TestOut out2 = new TestOut(1, "2");

		if (list.contains(out2)) {
			System.out.println("they are equal");
		}

	}

}
