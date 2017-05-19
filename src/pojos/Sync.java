package pojos;

import java.util.Arrays;

/**
 * Clase para poder usar la sync con el sync gateway y obtener el rev number
 * @author ivans
 *
 */
public class Sync {
	
	public class History{
		private String[] channels;
		private String[] revs;
		private int[] parents;
		
		public History()
		{
			
		}

		public String[] getChannels() {
			return channels;
		}

		public void setChannels(String[] channels) {
			this.channels = channels;
		}

		public String[] getRevs() {
			return revs;
		}

		public void setRevs(String[] revs) {
			this.revs = revs;
		}

		public int[] getParents() {
			return parents;
		}

		public void setParents(int[] parents) {
			this.parents = parents;
		}

		@Override
		public String toString() {
			return "History [channels=" + Arrays.toString(channels) + ", revs=" + Arrays.toString(revs) + ", parents="
					+ Arrays.toString(parents) + "]";
		}	
		
		
	}
	
	private String time_saved;
	private String sequence;
	private String rev;
	private History history;
	private int[] recent_sequences;
	
	public Sync()
	{
		
	}

	public String getTime_saved() {
		return time_saved;
	}

	public void setTime_saved(String time_saved) {
		this.time_saved = time_saved;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public int[] getRecent_sequences() {
		return recent_sequences;
	}

	public void setRecent_sequences(int[] recent_sequences) {
		this.recent_sequences = recent_sequences;
	}

	@Override
	public String toString() {
		return "Sync [time_saved=" + time_saved + ", sequence=" + sequence + ", rev=" + rev + ", history=" + history
				+ ", recent_sequences=" + Arrays.toString(recent_sequences) + "]";
	}
	
	
}
