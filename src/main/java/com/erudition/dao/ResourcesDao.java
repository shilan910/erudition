package com.erudition.dao;

import com.erudition.bean.CategoryEntity;
import com.erudition.bean.FilesEntity;
import com.erudition.bean.UserEntity;
import com.erudition.page.Page;
import com.erudition.page.PageHandler;
import com.erudition.util.MultipartFileUtils;
import com.erudition.util.ProduceThumb;
import com.erudition.util.nlpir.WordFrequency;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by tsj on 16-5-3.
 */
@Repository("resourcesDao")
public class ResourcesDao extends BaseDao {


    @Autowired
    @Qualifier("categoryDao")
    CategoryDao categoryDao;


    @Autowired
    private PageHandler pageHandler;

    public FilesEntity getById(int id) {
        return get(FilesEntity.class, id);
    }

    public Page<FilesEntity> getResourcesByPage(int pageNum, int pageSize, int thirdId) {
        String hql = "from FilesEntity as files where files.categoryId=?";
        Query query = query(hql);
        query.setInteger(0, thirdId);
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }

    public Page<FilesEntity> getResourcesByKeyword(int pageNum, int pageSize, String key) {
        String hql = "from FilesEntity as files where files.keywords like ?";
        Query query = query(hql);
        query.setString(0, "%" + key + "%");
        return pageHandler.getPage(pageNum, pageSize,
                FilesEntity.class, query);
    }


    public String saveFiles(String cate1, String cate2, String cate3, String keywords ,String originalName, MultipartFile file, UserEntity user){
        FilesEntity fileEntity = new FilesEntity();

        fileEntity.setTitle(originalName);
        fileEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        fileEntity.setSize(exchangeSize(file.getSize()));
        fileEntity.setCreater(user.getUserName());

        CategoryEntity category = categoryDao.getById(Integer.valueOf(cate3));




        //设置文件类型
        String type = originalName.substring(originalName.indexOf('.')+1,originalName.length());
        fileEntity.setType(type);

        fileEntity.setCategoryId(category.getId());
        fileEntity.setCategoryName(category.getCategoryName());

        String saveLocalUrl = categoryDao.getById(Integer.valueOf(cate1)).getCategoryName() + "/" +
                categoryDao.getById(Integer.valueOf(cate2)).getCategoryName() + "/" + category.getCategoryName();
        String url = MultipartFileUtils.saveFile(file, "/usr/local/erudition/"+saveLocalUrl , type);
        fileEntity.setUrl(url);


        //提取关键字
        String[] words = new String[100];
        String thumbPath = null;
        if(type=="docx" || type=="doc" || type=="txt"){
            WordFrequency wordFrequency = new WordFrequency();
            words = wordFrequency.wordFre(url,5);
        }else if(type=="wmv"){
            thumbPath = new ProduceThumb().processVideoThumb(url);
        }
        else if(type=="png" || type=="jpg"){
            try {
                thumbPath = new ProduceThumb().processPictureThumb(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        fileEntity.setKeywords(categoryDao.getById(Integer.valueOf(cate1)).getCategoryName() +
                categoryDao.getById(Integer.valueOf(cate2)).getCategoryName() +
                category.getCategoryName() + file.getOriginalFilename() + "#"+keywords+" "+words);

        fileEntity.setThumb(thumbPath);


        save(fileEntity);


        System.out.println("Dao out ......");

        String name = url.substring(url.lastIndexOf("/")+1);
        System.out.println("name:----->"+name);
        return name;
    }

    public List<FilesEntity> getByTitle(String title) {
        String hql = "from FilesEntity as files where files.title=?";
        Query query = query(hql);
        query.setString(0, title);
        return query.list();
    }


    private String getFileTypeByFileName(String name) {

        int index = name.lastIndexOf('.');

        return name.substring(index + 1);

    }

    private String exchangeSize(long size){
        double initsize = size;
        String nowsize;
        int layer=1;
        while(initsize>=1024){
            initsize/=1024.0;
            layer++;
        }
        DecimalFormat df2 = new DecimalFormat("###.00");
        String initsize2 = df2.format(initsize);
        System.out.println(layer);
        System.out.println(initsize2);
        switch (layer){
            case 1:nowsize = initsize2+"B";break;
            case 2:nowsize = initsize2+"KB";break;
            case 3:nowsize = initsize2+"MB";break;
            case 4:nowsize = initsize2+"GB";break;
            case 5:nowsize = initsize2+"TB";break;
            case 6:nowsize = initsize2+"PB";break;
            default:nowsize = initsize2+"B";break;
        }
        return nowsize;
    }


}
