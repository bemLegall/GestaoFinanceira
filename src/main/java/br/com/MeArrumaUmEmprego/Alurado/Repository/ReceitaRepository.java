package br.com.MeArrumaUmEmprego.Alurado.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.MeArrumaUmEmprego.Alurado.model.Receita;


public interface ReceitaRepository extends JpaRepository<Receita, Long>{

	boolean existsByDescricao(String descricao);

	
	@Query(value ="select * from receita r where r.descricao = :descricao ", nativeQuery = true)
	List <Receita> listDescricao(@Param("descricao")String descricao);
	
	Optional<Receita> findByDescricao(String descricao);
	
	
	@Query(value="SELECT * FROM receita WHERE YEAR(receita.data)=:ano and MONTH(receita.data)= :mes", nativeQuery = true)
	List<Receita>receitaPorMes(@Param("ano")String ano, @Param("mes")String mes);

	
	@Query(value="SELECT * FROM Receita WHERE YEAR(receita.data)=:ano and MONTH(receita.data)= :mes", nativeQuery = true)
	Optional<List<Receita>> mesEAno(@Param("ano")String ano, @Param("mes")String mes);

	@Query(value ="SELECT SUM(valor), MONTH(r.data) FROM Receita r WHERE MONTH(r.DATA)= :mes and year(r.data)= :ano", nativeQuery = true)
	List<Object[]> gastosDoMesReceita(@Param("ano") String ano, @Param("mes")String mes);
	
	
	
	
	
}