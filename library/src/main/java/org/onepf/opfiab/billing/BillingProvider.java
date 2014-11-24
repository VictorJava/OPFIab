/*
 * Copyright 2012-2014 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfiab.billing;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.onepf.opfiab.OPFIabHelper;
import org.onepf.opfiab.model.BillingProviderInfo;
import org.onepf.opfiab.model.Consumable;
import org.onepf.opfiab.model.Subscription;

public interface BillingProvider extends OPFIabHelper{

    @NonNull
    BillingProviderInfo getInfo();

    @NonNull
    BillingProviderConnection getConnection();

    @Override
    void purchase(@NonNull final Activity activity, @NonNull final Consumable consumable);

    @Override
    void consume(@Nullable final Activity activity, @NonNull final Consumable consumable);

    @Override
    void subscribe(@Nullable final Activity activity, @NonNull final Subscription subscription);

    @Override
    void inventory(@Nullable final Activity activity);

    @Override
    void skuInfo(@Nullable final Activity activity, @NonNull final String sku);
}
