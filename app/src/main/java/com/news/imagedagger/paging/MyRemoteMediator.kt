package com.news.imagedagger.paging


import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.news.imagedagger.api.ImageService
import com.news.imagedagger.db.ImageDatbase
import com.news.imagedagger.model.ImageDataItem
import com.news.imagedagger.utils.NetworkUtils
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class ImageRemoteMediator(
    private val imageService: ImageService,
    private val imageDatabase: ImageDatbase,
    private val applicationContext: Context
) : RemoteMediator<Int, ImageDataItem>() {
    private var currentPage = 1 // Track the current page

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ImageDataItem>
    ): MediatorResult {
        try {
            // Check network connectivity
            if (!isInternetAvailable()) {
                return MediatorResult.Error(IOException("No internet connection"))
            }


            val page = when (loadType) {
                LoadType.REFRESH -> {
                    // When refresh is called, start from the first page
                    currentPage = 1 // Reset page to 1
                    1
                }
                LoadType.PREPEND -> {

                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    // Load the next page
                    delay(5000)

                    currentPage++
                    currentPage
                }
            }

            //val response = imageService.getImages(page, state.config.pageSize)
            val response = imageService.getImages(page, 15) // Load 15 items per page

            if (response.isSuccessful && response.body() != null) {
                val images = response.body()!!

                imageDatabase.withTransaction {
                    // Clear data on refresh if it's the first page
                    if (loadType == LoadType.REFRESH) {
                        imageDatabase.imageDao().clearAll()
                    }
                    imageDatabase.imageDao().insertAll(images)
                }

                return MediatorResult.Success(endOfPaginationReached = images.isEmpty())
            } else {
                return MediatorResult.Error(HttpException(response))
            }
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private fun isInternetAvailable(): Boolean {
        return NetworkUtils.internetAvailable(applicationContext)
    }
}
