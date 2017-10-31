import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Hashtable;

class InsightDataScience {
	public static void main(String[] args) {
		Hashtable<String, Donator> ht = new Hashtable<String, Donator>();
		try {
			BufferedReader in = new BufferedReader(
					new FileReader("../input/itcont.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				String[] fields = line.split("\\|");
				// ignore OTHER_ID
				if (fields[15].length() != 0)
					continue;

				String key = fields[0] + "|" + fields[10].substring(0, 5);
				Donator donator = ht.get(key);
				if (donator == null) {
					donator = new Donator();
					ht.put(key, donator);
				}
				donator.transactions++;
				donator.amount += Integer.parseInt(fields[14]);

				int average = Math.round(
						(float) donator.amount / donator.transactions);

				System.out.println(key + "|" + average + "|" +
						donator.transactions + "| " + donator.amount);

/*
				System.out.println("CMTE_ID: " + fields[0]);
				System.out.println("ZIP_CODE: " + fields[10]);
				System.out.println("TRANSACTION_DT: " + fields[13]);
				System.out.println("TRANSACTION_AMT: " + fields[14]);
				*/
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	private static class Donator {
		int transactions;
		int amount;
	}
}
