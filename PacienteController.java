package interfacegrafica;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.PreparedStatement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PacienteController implements Initializable {
	@FXML
	private TextField txtCpfPac;
	@FXML
	private TextField txtNomePac;
	@FXML
	private TextField txtNascimentoPac;
	@FXML
	private TextField txtEnderecoPac;
	@FXML
	private TextField txtEmailPac;
	@FXML
	private TextField txtFonePac;
	@FXML
	private Button btnIncluir;
	@FXML
	private Button btnConsultar;
	@FXML
	private Button btnExcluir;
	@FXML
	private Button btnAlterar;
	@FXML
	private Button btnConfirmar;
	@FXML
	private Button btnCancelar;
	@FXML
	private Label lblMensagem;

	public static Connection abreBanco() {
		final String BANCO = "jdbc:mysql://localhost:3306/cacupe";

		try {
			return DriverManager.getConnection(BANCO, "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private int cpfPac;
	private String nomePac;
	private String nasciPac;
	private String enderecoPac;
	private String emailPac;
	private String fonePac;
	private String evento = "";
	
	public void onBtnIncluirClick() {
		evento = "incluir";

		txtCpfPac.setDisable(false);
		txtNomePac.setDisable(false);
		txtNascimentoPac.setDisable(false);
		txtEnderecoPac.setDisable(false);
		txtEmailPac.setDisable(false);
		txtFonePac.setDisable(false);

		btnIncluir.setDisable(true);
		btnConsultar.setDisable(true);
		btnExcluir.setDisable(true);
		btnAlterar.setDisable(true);
		btnCancelar.setDisable(false);
	}
	
	public void incluir() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		
		cpfPac = Integer.parseInt(txtCpfPac.getText());
		emailPac = txtEmailPac.getText();
		enderecoPac = txtEnderecoPac.getText();
		fonePac = txtFonePac.getText();
		nasciPac = txtNascimentoPac.getText();
		nomePac = txtNomePac.getText();
		
		try {
			sql = "INSERT INTO paciente (cpfPac, emailPac, enderecoPac, fonePac, nasciPac, nomePac) values (?,?,?,?,?,?)";
			ps = (PreparedStatement) banco.prepareStatement(sql);
						
			ps.setInt(1, cpfPac);
			ps.setString(2, emailPac);
			ps.setString(3, enderecoPac);
			ps.setString(4, fonePac);
			ps.setString(5, nasciPac);
			ps.setString(6, nomePac);
			
			int rsAltera = ps.executeUpdate();
			
			lblMensagem.setText(nomePac + " inclu�do(a) no banco de dados");
			}
			catch (SQLException e) {
				
				throw new RuntimeException(e);
			}
	}

	public void onBtnConsultarClick() {
		evento = "consultar";

		txtCpfPac.setDisable(false);
		txtNomePac.setDisable(true);
		txtNascimentoPac.setDisable(true);
		txtEnderecoPac.setDisable(true);
		txtEmailPac.setDisable(true);
		txtFonePac.setDisable(true);

		btnIncluir.setDisable(true);
		btnConsultar.setDisable(true);
		btnExcluir.setDisable(true);
		btnAlterar.setDisable(true);

		btnCancelar.setDisable(false);
	}

	public void consultar() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		boolean achou = false;

		// armazenar o c�digo do formul�rio na vari�vel
		cpfPac = Integer.parseInt(txtCpfPac.getText());

		try {
			// seleciona o CPF do paciente
			sql = "SELECT * FROM paciente WHERE CpfPac= " + cpfPac;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				// joga na tela
				txtCpfPac.setText(rs.getString("cpfPac"));
				txtNomePac.setText(rs.getString("nomePac"));
				txtNascimentoPac.setText(rs.getString("nasciPac"));
				txtEnderecoPac.setText(rs.getString("enderecoPac"));
				txtEmailPac.setText(rs.getString("emailPac"));
				txtFonePac.setText(rs.getString("fonePac"));
				lblMensagem.setText("");
				
				achou = true;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (!achou) {
			lblMensagem.setText("CPF n�o encontrado no banco de dados");
		}
	}

	public void onBtnExcluirClick() {
		excluir();
	}

		public void excluir() {
		Connection banco = abreBanco();
		PreparedStatement ps;
		ResultSet rs = null;
		String sql;
		
		cpfPac=Integer.parseInt(txtCpfPac.getText());
		try
		{
			sql="DELETE FROM paciente WHERE cpfPac= " + cpfPac;
			ps = (PreparedStatement) banco.prepareStatement(sql);
			int rsAltera = ps.executeUpdate();
			lblMensagem.setText(nomePac + " exclu�do(a) no banco de dados");
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void onBtnAlterarClick() {
		evento = "alterar";
		txtCpfPac.setDisable(false);
		txtNomePac.setDisable(false);
		txtNascimentoPac.setDisable(false);
		txtEnderecoPac.setDisable(false);
		txtEmailPac.setDisable(false);
		txtFonePac.setDisable(false);
		
	}

	public void alterar() {
		Connection banco=abreBanco();
		PreparedStatement ps;
		ResultSet rs=null;
		String sql;
		
		cpfPac = Integer.parseInt(txtCpfPac.getText());
		nomePac = txtNomePac.getText();
		enderecoPac = txtEnderecoPac.getText();
		nasciPac = txtNascimentoPac.getText();
		emailPac = txtEmailPac.getText();
		fonePac = txtFonePac.getText();
		
		try {
			sql="UPDATE paciente SET nomePac=?, emailPac=?, enderecoPac=?, fonePac=?, nasciPac=?, where cpfPac=?";
			
			ps = (PreparedStatement) banco.prepareStatement(sql);
			
			ps.setInt(1, cpfPac);
			ps.setString(2, emailPac);
			ps.setString(3, enderecoPac);
			ps.setString(4, fonePac);
			ps.setString(5, nasciPac);
			ps.setString(6, nomePac);

			ps.executeUpdate();
			ps.close();
			banco.close();
			lblMensagem.setText(nomePac + " alterado (a)");
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
			}

	}

	public void onBtnConfirmarClick() {
		if (evento.equalsIgnoreCase("incluir")) {
			// INCLUI paciente no BD
			incluir();
			onBtnCancelarClick();

		} else if (evento.equalsIgnoreCase("consultar")) {
			// CONSULTA paciente no BD

			btnExcluir.setDisable(false);
			btnAlterar.setDisable(false);
			consultar();
		} else if (evento.equalsIgnoreCase("alterar")) {
			alterar();
			onBtnCancelarClick();
		}
	}

	public void onBtnCancelarClick() {
		btnIncluir.setDisable(false);
		btnConsultar.setDisable(false);
		btnExcluir.setDisable(true);
		btnAlterar.setDisable(true);

		txtCpfPac.setText("");
		txtNomePac.setText("");
		txtNascimentoPac.setText("");
		txtEnderecoPac.setText("");
		txtEmailPac.setText("");
		txtFonePac.setText("");

		txtCpfPac.setDisable(true);
		txtNomePac.setDisable(true);
		txtNascimentoPac.setDisable(true);
		txtEnderecoPac.setDisable(true);
		txtEmailPac.setDisable(true);
		txtFonePac.setDisable(true);

		btnConfirmar.setDisable(true);
		btnCancelar.setDisable(true);

	}

	public void onKeyRelesead() {
		boolean confirmar;

		if (evento.equalsIgnoreCase("incluir")) {
			confirmar = txtCpfPac.getText().isEmpty()
					| txtNomePac.getText().isEmpty()
					| txtNascimentoPac.getText().isEmpty()
					| txtEnderecoPac.getText().isEmpty()
					| txtFonePac.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		} else if (evento.equalsIgnoreCase("consultar")) {
			confirmar = txtCpfPac.getText().isEmpty();
			btnConfirmar.setDisable(confirmar);
		} else if (evento.equalsIgnoreCase("alterar")) {
			// c�digo
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}