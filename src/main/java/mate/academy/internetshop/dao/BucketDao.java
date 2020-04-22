package mate.academy.internetshop.dao;

import mate.academy.internetshop.model.Bucket;

public interface BucketDao {
    Bucket createBucket(Bucket bucket);

    Bucket get(Long buckedId);

    Bucket update(Bucket bucket);
}
