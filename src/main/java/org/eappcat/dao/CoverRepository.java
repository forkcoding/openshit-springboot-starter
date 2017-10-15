package org.eappcat.dao;

import org.eappcat.entity.Cover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yuebo on 2017/10/15.
 */
@Repository
public interface CoverRepository extends JpaRepository<Cover,String> {

}
