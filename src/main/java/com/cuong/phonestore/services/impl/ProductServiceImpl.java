package com.cuong.phonestore.services.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.cuong.phonestore.model.Brand;
import com.cuong.phonestore.model.Category;
import com.cuong.phonestore.model.Product;
import com.cuong.phonestore.repository.ProductRepository;
import com.cuong.phonestore.services.ProductService;
import com.cuong.phonestore.utils.CommonUtil;
import com.cuong.phonestore.utils.Constants;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Value("${LOCAL_PATH_PARENT_FOLDER}")
	private String parentFolderPath;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product findById(String id) {
		Optional<Product> optional = productRepository.findById(id);
		return  optional.isPresent() ? (Product) optional.get() : null;
	}

	@Override
    @Cacheable(key = "#pageable")
	//    @CacheEvict(key="#pageable", allEntries = true)
    public List<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    @Cacheable(key = "{#pageable,#brand.branID}")
    public List<Product> findAllByBrand(Pageable pageable, Brand brand) {
        return productRepository.findAllByBrand(pageable, brand);
    }

    @Override
    public List<Product> findTop8ByOrderByDateCreatedDesc() {
        return productRepository.findTop8ByOrderByDateCreatedDesc();
    }

    /*@Override
    @Cacheable(key = "#root.methodName")
    public List<Product> findTop8ByOrderBySellCountDesc() {
        return productRepository.findTop8ByOrderBySellCountDesc();
    }

    @Override
    @CachePut(key = "'findTop8ByOrderBySellCountDesc'")
    public List<Product> findTop8ByOrderBySellCountDescCacheRefresh() {
        return productRepository.findTop8ByOrderBySellCountDesc();
    }*/

    @Override
    @Cacheable(key = "{#brand.branID,#id}")
    public List<Product> getRelatedProducts(Brand brand, String id) {
        /*List<Product> returnList = productRepository.findTop8ByBrandAndIdIsNotOrderBySellCountDesc(brand, id);
        if (returnList.size() < 8) {
            returnList.addAll(productRepository.findAllByProductCategoryIsNotOrderBySellCountDesc(brand, PageRequest.of(0, 8 - returnList.size())));
        }*/
        return null;
    }

    @Override
    public List<Product> searchProducts(String keyword, Integer page, Integer size) {
        if (page == null || size == null) {
            throw new IllegalArgumentException("Page and size parameters are required");
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        return productRepository.findAllByProductNameContaining(keyword, pageRequest);
    }

	@Override
	public void delete(Product product) {
		deleteFile(product.getImageUrl());
		product.setBrand(null);
		product.setCategory(null);
		productRepository.delete(product);
	}

	@Override
	public void create(Product product) {
		try {
			String imagePath = saveImage(parentFolderPath, product.getImageUrl(), null);
			product.setImageUrl(imagePath);;
		} catch (IOException e) {
			e.printStackTrace();
		}
		productRepository.save(product);
	}

	@Override
	public void edit(Product product, String oldImagePath) {
		deleteFile(oldImagePath);
		try {
			String imagePath = saveImage(parentFolderPath, product.getImageUrl(), oldImagePath);
			product.setImageUrl(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		update(product);
	}

	@Override
	public void update(Product product) {
		productRepository.save(product);
	}
	
	/**
	 * Save image to storage folder
	 * 
	 * @param parentFolderPath
	 * @param fileInBase64
	 * @param oldImagePath
	 * @return assetPath
	 * @throws IOException
	 */
	private String saveImage(String parentFolderPath, String fileInBase64, String oldImagePath)
			throws IOException {

		// Generate assetName with format: yyyyMMdd-HHmm-${randomStr}
		SecureRandom random = new SecureRandom();
		String randomStr = new BigInteger(130, random).toString(32).substring(0, 6);
		String assetName = CommonUtil.cvDateToString(new Date(), Constants.DATE_FORMAT_FOR_FILE_NAME) + Constants.COMMON_HYPHEN
				+ randomStr;

		String rootFolderPath = Constants.PROP_ROOT_FOLDER;
		String assetPath = assetName + Constants.SUFFIX_IMAGE_PNG;
		String fullAssetPath = rootFolderPath + parentFolderPath + assetPath;

		// Decode File From Base64 Encoding To File Image
		Base64 decoder = new Base64();
		byte[] imgBytes = decoder.decode(fileInBase64);

		// Create Folder To Save Image
		File parentFolder = new File(rootFolderPath + parentFolderPath);
		if (!parentFolder.exists()) {
			parentFolder.mkdir();
		}

		if (StringUtils.isNotEmpty(oldImagePath)) {

			// Remove image if it existed
			File imageFile = new File(rootFolderPath + oldImagePath);
			if (imageFile.exists() && imageFile.length() > 0) {
				imageFile.delete();
			}
		}

		// Save File To Disk
		FileCopyUtils.copy(imgBytes, new File(fullAssetPath));
		return assetPath;
	}
	
	/*
	 * Delete File
	 * 
	 * @param imageUrl
	 * @return result
	 */
	private boolean deleteFile(String pathName) {
		boolean isDeletedFile = false;
		String rootFolderPath = Constants.PROP_ROOT_FOLDER;
		String fullAssetPath = rootFolderPath + pathName;
		File file = new File(fullAssetPath);
		if (file.exists()) {
			isDeletedFile = file.delete();
			System.out.println("Delete file:"+ pathName);
		}
		return isDeletedFile;
	}

	@Override
	public boolean exitsById(String productID) {
		return productRepository.existsById(productID);
	}

	@Override
	public Long countAll() {
		return productRepository.count();
	}

	@Override
	public List<Product> findAllByCategory(Pageable pageable, Category category) {
		return productRepository.findAllByCategory(pageable, category);
	}
}
