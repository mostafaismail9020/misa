/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.sap.ibso.eservices.storefront.controllers;

import de.hybris.platform.acceleratorcms.model.components.*;
import com.sap.ibso.eservices.core.model.SagiaRealTimeOnlineSupportComponentModel;
import com.sap.ibso.eservices.core.model.SagiaDashboardBannerCarouselComponentModel;
import de.hybris.platform.cms2lib.model.components.ProductCarouselComponentModel;
import de.hybris.platform.cms2.model.contents.components.CMSLinkComponentModel;
/*
 * Suppress sonar warning (squid:S2068 | Credentials should not be hard-coded
 * Suppress sonar warning (squid:S00115 | Constant names should comply with a naming convention
 * Suppress sonar warning (squid:S1214 | Constants should not be defined in interfaces
 * Constant names cannot be changed due to their usage in dependant extensions
 */
@SuppressWarnings({"squid:S2068","squid:S00115","squid:S1214"})
public interface ControllerConstants
{

	/**
	 * Class with action name constants
	 */
	interface Actions
	{
		interface Cms 
		{
			String _Prefix = "/view/"; 
			String _Suffix = "Controller"; 

			/**
			 * Default CMS component controller
			 */
			String DefaultCMSComponent = _Prefix + "DefaultCMSComponentController"; 

			/**
			 * CMS components that have specific handlers
			 */
			String NavigationBarComponent = _Prefix + NavigationBarComponentModel._TYPECODE + _Suffix; 
			String DynamicBannerComponent = _Prefix + DynamicBannerComponentModel._TYPECODE + _Suffix; 
			String SimpleResponsiveBannerComponent = _Prefix + SimpleResponsiveBannerComponentModel._TYPECODE + _Suffix; 
			String CMSTabParagraphContainer = _Prefix + CMSTabParagraphContainerModel._TYPECODE + _Suffix; 
			String SagiaRealTimeOnlineSupportComponent = _Prefix + SagiaRealTimeOnlineSupportComponentModel._TYPECODE + _Suffix;
			String SagiaDashboardBannerCarouselComponent = _Prefix + SagiaDashboardBannerCarouselComponentModel._TYPECODE + _Suffix;
			
			/** Invest Saudi Code */
			String PurchasedCategorySuggestionComponent = _Prefix + PurchasedCategorySuggestionComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CartSuggestionComponent = _Prefix + CartSuggestionComponentModel._TYPECODE + _Suffix; // NOSONAR
			String ProductReferencesComponent = _Prefix + ProductReferencesComponentModel._TYPECODE + _Suffix; // NOSONAR
			String ProductCarouselComponent = _Prefix + ProductCarouselComponentModel._TYPECODE + _Suffix; // NOSONAR
			String MiniCartComponent = _Prefix + MiniCartComponentModel._TYPECODE + _Suffix; // NOSONAR
			String ProductFeatureComponent = _Prefix + ProductFeatureComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CategoryFeatureComponent = _Prefix + CategoryFeatureComponentModel._TYPECODE + _Suffix; // NOSONAR
			String CMSLinkComponent = _Prefix + CMSLinkComponentModel._TYPECODE + _Suffix; // NOSONAR
			String SubCategoryListComponent = _Prefix + SubCategoryListComponentModel._TYPECODE + _Suffix; // NOSONAR
		}
	}

	/**
	 * Class with view name constants
	 */
	interface Views
	{
		interface Cms 
		{
			String ComponentPrefix = "cms/"; 
			interface RealTime 
			{
				String SagiaRealTimeOnlineSupportComponent = ComponentPrefix + "sagiarealtimeonlinesupportcomponent";
				String SagiaDashboardBannerCarouselComponent = ComponentPrefix + "sagiadashboardbannercarouselcomponent";
			}
		}

		interface Pages
		{


				interface Account 
			{
				String AccountLoginPage = "pages/account/accountLoginPage"; 
				String AccountProfilePage = "pages/account/sagiaProfileCompany";
				String VerificationPage = "pages/account/sagiaVerification";
				String SubscriptionFeeCheckPage = "pages/account/sagiaSubscriptionFeeCheck";
				/** Invest Saudi Code */
				String AccountHomePage = "pages/account/accountHomePage"; // NOSONAR
				String AccountOrderHistoryPage = "pages/account/accountOrderHistoryPage"; // NOSONAR
				String AccountOrderPage = "pages/account/accountOrderPage"; // NOSONAR
				/**String AccountProfilePage = "pages/account/accountProfilePage"; */ // NOSONAR
				String AccountProfileEditPage = "pages/account/accountProfileEditPage"; // NOSONAR
				String AccountProfileEmailEditPage = "pages/account/accountProfileEmailEditPage"; // NOSONAR
				String AccountChangePasswordPage = "pages/account/accountChangePasswordPage"; // NOSONAR
				String AccountAddressBookPage = "pages/account/accountAddressBookPage"; // NOSONAR
				String AccountEditAddressPage = "pages/account/accountEditAddressPage"; // NOSONAR
				String AccountPaymentInfoPage = "pages/account/accountPaymentInfoPage"; // NOSONAR
				String AccountRegisterPage = "pages/account/accountRegisterPage"; // NOSONAR
			}
			
			/** Invest Saudi Code */
			interface Checkout // NOSONAR
			{
				String CheckoutRegisterPage = "pages/checkout/checkoutRegisterPage"; // NOSONAR
				String CheckoutConfirmationPage = "pages/checkout/checkoutConfirmationPage"; // NOSONAR
				String CheckoutLoginPage = "pages/checkout/checkoutLoginPage"; // NOSONAR
			}
            
			/** Invest Saudi Code */
			interface MultiStepCheckout // NOSONAR
			{
				String AddEditDeliveryAddressPage = "pages/checkout/multi/addEditDeliveryAddressPage"; // NOSONAR
				String ChooseDeliveryMethodPage = "pages/checkout/multi/chooseDeliveryMethodPage"; // NOSONAR
				String ChoosePickupLocationPage = "pages/checkout/multi/choosePickupLocationPage"; // NOSONAR
				String AddPaymentMethodPage = "pages/checkout/multi/addPaymentMethodPage"; // NOSONAR
				String CheckoutSummaryPage = "pages/checkout/multi/checkoutSummaryPage"; // NOSONAR
				String HostedOrderPageErrorPage = "pages/checkout/multi/hostedOrderPageErrorPage"; // NOSONAR
				String HostedOrderPostPage = "pages/checkout/multi/hostedOrderPostPage"; // NOSONAR
				String SilentOrderPostPage = "pages/checkout/multi/silentOrderPostPage"; // NOSONAR
				String GiftWrapPage = "pages/checkout/multi/giftWrapPage"; // NOSONAR
			}


			interface Password 
			{
				String PasswordResetChangePage = "pages/password/passwordResetChangePage"; 
				String PasswordResetRequest = "pages/password/passwordResetRequestPage"; 
				String PasswordResetRequestConfirmation = "pages/password/passwordResetRequestConfirmationPage"; 
			}

			interface Error 
			{
				String ErrorNotFoundPage = "pages/error/errorNotFoundPage"; 
			}
			
			/** Invest Saudi Code */
			interface Cart // NOSONAR
			{
				String CartPage = "pages/cart/cartPage"; // NOSONAR
			}
            
			/** Invest Saudi Code */
			interface StoreFinder // NOSONAR
			{
				String StoreFinderSearchPage = "pages/storeFinder/storeFinderSearchPage"; // NOSONAR
				String StoreFinderDetailsPage = "pages/storeFinder/storeFinderDetailsPage"; // NOSONAR
				String StoreFinderViewMapPage = "pages/storeFinder/storeFinderViewMapPage"; // NOSONAR
			}


			interface Misc 
			{
				String MiscRobotsPage = "pages/misc/miscRobotsPage"; 
				String MiscSiteMapPage = "pages/misc/miscSiteMapPage"; 
			}

			interface Guest 
			{ 
				String GuestOrderPage = "pages/guest/guestOrderPage";
				/** Invest Saudi Code */
				String GuestOrderErrorPage = "pages/guest/guestOrderErrorPage"; // NOSONAR
			}
			
			/** Invest Saudi Code */
			interface Product // NOSONAR
			{
				String WriteReview = "pages/product/writeReview"; // NOSONAR
				String OrderForm = "pages/product/productOrderFormPage"; // NOSONAR
			}
			
			/** Invest Saudi Code */
			interface QuickOrder // NOSONAR
			{
				String QuickOrderPage = "pages/quickOrder/quickOrderPage"; // NOSONAR
			}
			
			/** Invest Saudi Code */
			interface CSV // NOSONAR
			{
				String ImportCSVSavedCartPage = "pages/csv/importCSVSavedCartPage"; // NOSONAR
			}


		}

		interface Fragments
		{
			/** Invest Saudi Code */
			interface Cart // NOSONAR
			{
				String AddToCartPopup = "fragments/cart/addToCartPopup"; // NOSONAR
				String MiniCartPanel = "fragments/cart/miniCartPanel"; // NOSONAR
				String MiniCartErrorPanel = "fragments/cart/miniCartErrorPanel"; // NOSONAR
				String CartPopup = "fragments/cart/cartPopup"; // NOSONAR
				String ExpandGridInCart = "fragments/cart/expandGridInCart"; // NOSONAR
			}

			/** Invest Saudi Code */
			interface Account // NOSONAR
			{
				String CountryAddressForm = "fragments/address/countryAddressForm"; // NOSONAR
				String SavedCartRestorePopup = "fragments/account/savedCartRestorePopup"; // NOSONAR
			}

			interface Checkout 
			{
				String TermsAndConditionsPopup = "fragments/checkout/termsAndConditionsPopup";
				/** Invest Saudi Code */
				String BillingAddressForm = "fragments/checkout/billingAddressForm"; // NOSONAR
				String ReadOnlyExpandedOrderForm = "fragments/checkout/readOnlyExpandedOrderForm"; // NOSONAR
			}

			interface Password 
			{
				String PasswordResetRequestPopup = "fragments/password/passwordResetRequestPopup"; 
				String ForgotPasswordValidationMessage = "fragments/password/forgotPasswordValidationMessage"; 
			}

			interface License
			{
				String LicenseApplyExistingShareholderForm = "fragments/license/shareholder/licenseApplyExistingShareholderFormFragment";
				String LicenseApplyRegisteredShareholdersTable = "fragments/license/shareholder/licenseApplyRegisteredShareholdersTableFragment";
				String LicenseApplyPersonShareholderForm = "fragments/license/shareholder/licenseApplyPersonShareholderFormFragment";
				String LicenseApplyOrganizationShareholderForm = "fragments/license/shareholder/licenseApplyOrganizationShareholderFormFragment";
			}
			
			/** Invest Saudi Code */
			interface Product // NOSONAR
			{
				String FutureStockPopup = "fragments/product/futureStockPopup"; // NOSONAR
				String QuickViewPopup = "fragments/product/quickViewPopup"; // NOSONAR
				String ZoomImagesPopup = "fragments/product/zoomImagesPopup"; // NOSONAR
				String ReviewsTab = "fragments/product/reviewsTab"; // NOSONAR
				String StorePickupSearchResults = "fragments/product/storePickupSearchResults"; // NOSONAR
			}

		}
	}
}
