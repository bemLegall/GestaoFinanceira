package br.com.MeArrumaUmEmprego.Alurado.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.MeArrumaUmEmprego.Alurado.model.Despesa;


@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {

	boolean existsByDescricao(String descricao);
	
	@Query(value ="select * from despesa d where d.descricao = :descricao ", nativeQuery = true)
	List <Despesa> listDescricao(@Param("descricao")String descricao);

	Optional<Despesa> findByDescricao(String descricao);

	
	
	@Query(value="SELECT * FROM despesa where YEAR(despesa.data)=:ano and MONTH(despesa.data)= :mes", nativeQuery = true)
	List<Despesa>listaDespesaAnoEMes(@Param("ano")String ano,  @Param("mes")String mes);
	
	
	@Query(value="SELECT * FROM despesa where YEAR(despesa.data)=:ano and MONTH(despesa.data)= :mes", nativeQuery = true)
	Optional<List<Despesa>>ChecarSerExisteMesEANo(@Param("ano") String ano, @Param("mes")String mes);
	
	
}
