package io.reactivesw.order.application.model.mapper;

import io.reactivesw.order.application.model.ImageView;
import io.reactivesw.order.domain.model.value.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * image mapper.
 */
public final class ImageMapper {

  /**
   * private default constructor.
   */
  private ImageMapper() {
  }

  /**
   * to entity.
   *
   * @param imageView
   * @return
   */
  public static Image toEntity(ImageView imageView) {
    Image image = null;
    if (imageView != null) {
      image = new Image();
      image.setLabel(imageView.getLabel());
      image.setUrl(imageView.getUrl());
    }
    return image;
  }

  /**
   * list view to list entity.
   *
   * @param viewList
   * @return
   */
  public static List<Image> toEntities(List<ImageView> viewList) {
    List<Image> images = new ArrayList<>();
    if (viewList != null && !viewList.isEmpty()) {
      viewList.stream().forEach(imageView -> images.add(toEntity(imageView)));
    }
    return images;
  }

  /**
   * to view.
   *
   * @param image
   * @return
   */
  public static ImageView toView(Image image) {
    ImageView imageView = null;
    if (image != null) {
      imageView = new ImageView();
      imageView.setLabel(image.getLabel());
      imageView.setUrl(image.getUrl());
    }
    return imageView;
  }

  /**
   * list entity to list view.
   *
   * @param images
   * @return
   */
  public static List<ImageView> toViews(List<Image> images) {
    List<ImageView> imageViews = new ArrayList<>();
    if (images != null && !images.isEmpty()) {
      images.stream().forEach(image -> imageViews.add(toView(image)));
    }
    return imageViews;
  }
}
