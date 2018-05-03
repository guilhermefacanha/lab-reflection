package main;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entity.Usuario;

public class ReflectionMethodLab {
	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		Usuario usuario = Usuario.builder()
				.data(new Date())
				.nome("Guilherme Facanha")
				.login("guilherme")
				.salario(1000.50)
				.telefone("88 8888-8888").build();
		
		System.out.println("Digite a informacao do usuario que deseja buscar:");
		String campo = keyboard.nextLine();
		
		if (!existeCampo(usuario,campo))
			System.out.println("Nao foi encontrado o campo digitado favor tente novamente");
		else {
			try {
				String valor = getValorCampo(usuario,campo);
				System.out.println("Valor do campo pesquisado: "+valor);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static String getValorCampo(Usuario usuario, String campo) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String campoMetName = getCampoMethodName(campo);
		Class classe = usuario.getClass();
		Method[] allMethods = classe.getDeclaredMethods();
		for(Method m : allMethods) {
			String mname = m.getName();
			if(mname.equals(campoMetName))
			{
				Object o = m.invoke(usuario);
				return o.toString();
			}
		}
		return null;
	}

	private static String getCampoMethodName(String campo) {
		Character c = campo.charAt(0);
		String methodName= "get"+c.toString().toUpperCase() + campo.substring(1, campo.length());
		return methodName;
	}

	private static boolean existeCampo(Usuario usuario, String campo) {
		Class classe = usuario.getClass();
		Field[] fields = classe.getDeclaredFields();
		for (Field field : fields) {
			String attName = field.getName();
			System.out.println("Atributo: " + attName);
			if (attName.equals(campo))
				return true;
		}
		return false;
	}
}
