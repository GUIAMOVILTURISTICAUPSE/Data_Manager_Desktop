package pojos;

	public class Senderos {
		@Override
		public String toString() {
			return "NombreSendero=" + NombreSendero ;
		}

		public String getNombreSendero() {
			return NombreSendero;
		}

		public void setNombreSendero(String nombreSendero) {
			NombreSendero = nombreSendero;
		}

		private String NombreSendero;
		
		
	}


