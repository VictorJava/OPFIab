/*
 * Copyright 2012-2015 One Platform Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onepf.opfiab.model.event.billing;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.onepf.opfiab.misc.OPFIabActivity;
import org.onepf.opfutils.OPFLog;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class PurchaseRequest extends BillingRequest {

    private static final String NAME_SKU = "sku";


    @SuppressFBWarnings({"NFF_NON_FUNCTIONAL_FIELD"})
    @Nullable
    private final transient Reference<Activity> activityReference;
    @NonNull
    private final String sku;
    private final boolean needsFakeActivity;

    @SuppressFBWarnings({"SE_NO_SERIALVERSIONID"})
    public PurchaseRequest(@Nullable final Activity activity,
                           @NonNull final String sku,
                           final boolean needsFakeActivity) {
        super(Type.PURCHASE);
        this.needsFakeActivity = needsFakeActivity;
        this.activityReference = activity == null ? null : new WeakReference<>(activity);
        this.sku = sku;
    }

    public PurchaseRequest(@NonNull final Activity activity,
                           @NonNull final String sku) {
        this(activity, sku, false);
    }

    public PurchaseRequest(@NonNull final String sku) {
        this(null, sku, true);
    }

    @Nullable
    public Activity getActivity() {
        return activityReference == null ? null : activityReference.get();
    }

    @NonNull
    public String getSku() {
        return sku;
    }

    public boolean needsFakeActivity() {
        return needsFakeActivity;
    }

    public boolean isActivityFake() {
        return getActivity() instanceof OPFIabActivity;
    }

    @NonNull
    @Override
    public JSONObject toJson() {
        final JSONObject jsonObject = super.toJson();
        try {
            jsonObject.put(NAME_SKU, sku);
        } catch (JSONException exception) {
            OPFLog.e("", exception);
        }
        return jsonObject;
    }

    //CHECKSTYLE:OFF
    @SuppressWarnings({"PMD", "RedundantIfStatement"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final PurchaseRequest that = (PurchaseRequest) o;

        if (!sku.equals(that.sku)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + sku.hashCode();
        return result;
    }
    //CHECKSTYLE:ON
}
