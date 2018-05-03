package entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Usuario {
	private String nome;
	private String telefone;
	private String login;
	private Date data;
	private double salario;
}
