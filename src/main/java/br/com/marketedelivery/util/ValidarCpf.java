package br.com.marketedelivery.util;

public class ValidarCpf {
	private String cpf;

	public ValidarCpf() {
	}
	
	public boolean validarCpf(String cpf) 
	{
		if (cpf.equals("000.000.000-00") || cpf.equals("111.111.111-11") || cpf.equals("222.222.222-22")
				|| cpf.equals("333.333.333-33") || cpf.equals("444.444.444-44") || cpf.equals("555.555.555-55")
				|| cpf.equals("666.666.666-66") || cpf.equals("777.777.777-77") || cpf.equals("888.888.888-88")
				|| cpf.equals("999.999.999-99") || (cpf.length() < 11) || (cpf.length() < 14)|| cpf == null)
		{
			return false;
		}
			
		else 
		{
			String cpfGerado = "";
			this.cpf = cpf;
			removerCaracteres();
			if (verificarSeTamanhoInvalido(this.cpf))
				return false;
			if (verificarSeDigIguais(this.cpf))
				return false;
			cpfGerado = this.cpf.substring(0, 9);
			cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
			cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

			if (!cpfGerado.equals(this.cpf))
				return false;
		}
		return true;
	}

	private void removerCaracteres() {
		this.cpf = this.cpf.replace("-", "");
		this.cpf = this.cpf.replace(".", "");
	}

	private boolean verificarSeTamanhoInvalido(String cpf) {
		if (cpf.length() != 11)
			return true;
		return false;
	}

	private boolean verificarSeDigIguais(String cpf) {
		// char primDig = cpf.charAt(0);
		char primDig = '0';
		char[] charCpf = cpf.toCharArray();
		for (char c : charCpf)
			if (c != primDig)
				return false;
		return true;
	}

	private String calculoComCpf(String cpf) {
		int digGerado = 0;
		int mult = cpf.length() + 1;
		char[] charCpf = cpf.toCharArray();
		for (int i = 0; i < cpf.length(); i++)
			digGerado += (charCpf[i] - 48) * mult--;
		if (digGerado % 11 < 2)
			digGerado = 0;
		else
			digGerado = 11 - digGerado % 11;
		return String.valueOf(digGerado);
	}
}
