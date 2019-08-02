package com.yong.mark.repository;

import java.util.List;

public interface PatronRepositoryCustom {

    List<String> findPatronListWithoutScannedId();
}
