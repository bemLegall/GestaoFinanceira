package br.com.MeArrumaUmEmprego.Alurado.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.MeArrumaUmEmprego.Alurado.model.Resumo;

@Repository
public interface ResumoRepository extends JpaRepository<Resumo, Long> {

	@Query(value = "SELECT * FROM resumo WHERE MONTH(resumo.data) = :mes AND YEAR(resumo.data) = :ano", nativeQuery = true)
	List<Resumo> resumoDoMes(@Param("mes") String mes, @Param("ano") String ano);

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO resumo (despesa_do_mes,`data`)\r\n"
			+ "SELECT sum(despesa.valor), despesa.data from despesa \r\n"
			+ "WHERE MONTH(despesa.data) = :mes AND YEAR(despesa.data) = :ano", nativeQuery = true)
	void resumoDespesaInsert(@Param("mes") String mes, @Param("ano") String ano);

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO resumo (receita_do_mes,`data`)\r\n"
			+ "SELECT sum(receita.valor), receita.data from receita \r\n"
			+ "WHERE MONTH(receita.data) = :mes AND YEAR(receita.data) = :ano", nativeQuery = true)
	void resumoReceitaInsert(@Param("mes") String mes, @Param("ano") String ano);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo \r\n" + "SET receita_do_mes =(SELECT SUM(receita.valor) FROM receita\r\n"
			+ "WHERE MONTH(receita.data) = MONTH(resumo.data) AND YEAR(receita.data) = YEAR(resumo.data));", nativeQuery = true)
	void updateReceita(@Param("mes") String mes, @Param("ano") String ano);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo \r\n" + "SET despesa_do_mes =(SELECT SUM(despesa.valor) FROM despesa\r\n"
			+ "WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data));", nativeQuery = true)
	void updateDespesa(@Param("mes") String mes, @Param("ano") String ano);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET saldo_do_mes = resumo.receita_do_mes - resumo.despesa_do_mes", nativeQuery = true)
	void updateSaldo(@Param("mes") String mes, @Param("ano") String ano);

	@Query(value = "SELECT EXISTS(SELECT resumo.data FROM resumo WHERE MONTH(resumo.data) = :mes AND YEAR(resumo.data) = :ano)", nativeQuery = true)
	int check(@Param("mes") String mes, @Param("ano") String ano);

	@Query(value = "SELECT EXISTS(SELECT despesa.data FROM despesa WHERE MONTH(despesa.data) = :mes AND YEAR(despesa.data) = :ano)", nativeQuery = true)
	int checkDespesa(@Param("mes") String mes, @Param("ano") String ano);

	@Query(value = "SELECT EXISTS(SELECT receita.data FROM receita WHERE MONTH(receita.data) = :mes AND YEAR(receita.data) = :ano)", nativeQuery = true)
	int checkReceita(@Param("mes") String mes, @Param("ano") String ano);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_alimentacao =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'ALIMENTAÇÃO')", nativeQuery = true)
	void updateCategoriaAlimentacao();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_outros =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'OUTROS')", nativeQuery = true)
	void updateCategoriaOutros();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_lazer =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'LAZER')", nativeQuery = true)
	void updateCategoriaLazer();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_transporte =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'TRANSPORTE')", nativeQuery = true)
	void updateCategoriaTransporte();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_moradia =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'MORADIA')", nativeQuery = true)
	void updateCategoriaMoradia();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_imprevistos =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'IMPREVISTOS')", nativeQuery = true)
	void updateCategoriaImprevisto();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE resumo SET gastos_com_educacao =(SELECT SUM(despesa.valor) FROM despesa WHERE MONTH(despesa.data) = MONTH(resumo.data) AND YEAR(despesa.data) = YEAR(resumo.data) AND despesa.categoria =  'EDUCACÃO')", nativeQuery = true)
	void updateCategoriaEducacao();
	


}
